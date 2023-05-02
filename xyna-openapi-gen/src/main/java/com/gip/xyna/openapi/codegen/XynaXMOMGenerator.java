package com.gip.xyna.openapi.codegen;

import io.swagger.codegen.*;
import io.swagger.models.Model;
import io.swagger.models.Swagger;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.properties.*;

import java.util.*;
import java.util.Map.Entry;
import java.io.File;

public class XynaXMOMGenerator extends DefaultCodegen implements CodegenConfig {

  // source folder where to write the files
  protected String sourceFolder = "XMOM";
  protected String apiVersion = "1.0.0";
  protected String xynaFactoryVersion = "8.2.11.14";
  protected String xynaOpenAPIVersion = "1.0.0";

  public static final String XYNA_FACTORY_VERSION = "xynaFactoryVersion";
  public static final String XYNA_OPENAPI_VERSION = "xynaOpenAPIVersion";
  public static final String PROJECT_NAME = "projectName";
  public static final String MODULE_NAME = "moduleName";
  public static final String PROJECT_DESCRIPTION = "projectDescription";
  public static final String PROJECT_VERSION = "projectVersion";

  protected String projectName;
  protected String moduleName;
  protected String invokerPackage;
  protected String projectDescription;
  protected String projectVersion;
  protected String licenseName;

  /**
   * Configures the type of generator.
   * 
   * @return the CodegenType for this generator
   * @see io.swagger.codegen.CodegenType
   */
  public CodegenType getTag() {
    return CodegenType.CLIENT;
  }

  @Override
  public String escapeQuotationMark(String input) {
    if (input == null) {
      return null;
    }
    // remove ', " to avoid code injection
    return input.replace("\"", "").replace("'", "");
  }

  @Override
  public String escapeUnsafeCharacters(String input) {
    if (input == null) {
      return null;
    }
    return input.replace("&", "&amp;").replace("[CDATA", "[_CDATA").replace(">", "&lt;")
        .replace(">", "&gt;");
  }

  @Override
  public String escapeText(String input) {
    if (input == null) {
      return null;
    }
    return escapeUnsafeCharacters(input);
  }

  @Override
  public String toVarName(String name) {
    // sanitize name
    String myName = sanitizeName(name).replaceAll("[^a-zA-Z0-9_]+", "_");

    if ("_".equals(myName)) {
      myName = "_v";
    }

    // for reserved word or word starting with number
    if (isReservedWord(myName) || myName.matches("^\\d.*")) {
      myName = escapeReservedWord(myName);
    }

    return myName;
  }

  @Override
  public String toParamName(String name) {
    // should be the same as variable name
    return toVarName(name);
  }

  @Override
  public String toModelName(String name) {
    name = sanitizeName(name);

    if (modelNamePrefix != null && modelNamePrefix.length() > 0) {
      name = modelNamePrefix + "_" + name;
    }

    if (modelNameSuffix != null && modelNameSuffix.length() > 0) {
      name = name + "_" + modelNameSuffix;
    }

    // camelize the model name
    // phone_number => PhoneNumber
    name = camelize(name);

    // model name cannot use reserved keyword, e.g. return
    if (isReservedWord(name)) {
      String modelName = "Model" + name;
      LOGGER.warn(name + " (reserved word) cannot be used as model name. Renamed to " + modelName);
      return modelName;
    }

    // model name starts with number
    if (name.matches("^\\d.*")) {
      String modelName = "Model" + name; // e.g. 200Response => Model200Response (after camelize)
      LOGGER
          .warn(name + " (model name starts with number) cannot be used as model name. Renamed to "
              + modelName);
      return modelName;
    }

    return name;
  }

  @Override
  public String toModelFilename(String name) {
    // should be the same as the model name
    return toModelName(name);
  }

  /*
   * @Override public String toEnumName(CodegenProperty property) { return
   * super.toEnumName(property); }
   * 
   * @Override public String toEnumValue(String value, String datatype) { return
   * super.toEnumValue(value, datatype); }
   * 
   * @Override public String toEnumVarName(String value, String datatype) { return
   * super.toEnumVarName(value, datatype); }
   */
  /**
   * Configures a friendly name for the generator. This will be used by the generator to select the
   * library with the -l flag.
   * 
   * @return the friendly name for the generator
   */
  public String getName() {
    return "XynaXMOM";
  }

  /**
   * Returns human-friendly help for the generator. Provide the consumer with help tips, parameters
   * here
   * 
   * @return A string value for the help message
   */
  public String getHelp() {
    return "Generates a XynaXMOM client library.";
  }

  public XynaXMOMGenerator() {
    super();

    // set the output folder here
    outputFolder = "generated-code/XynaXMOM";

    /**
     * Models. You can write model files using the modelTemplateFiles map. if you want to create one
     * template for file, you can do so here. for multiple files for model, just put another entry
     * in the `modelTemplateFiles` with a different extension
     */
    modelTemplateFiles.put("model.mustache", // the template to use
        ".xml"); // the extension for each file to write

    /**
     * Api classes. You can write classes for each Api file with the apiTemplateFiles map. as with
     * models, add multiple entries with different extensions for multiple files per class
     *//*
        * apiTemplateFiles.put( "api.mustache", // the template to use ".sample"); // the extension
        * for each file to write
        */
    /**
     * Template Location. This is the location which templates will be read from. The generator will
     * use the resource stream to attempt to read the templates.
     */
    templateDir = "XynaXMOM";

    /**
     * Api Package. Optional, if needed, this can be used in templates
     */
    apiPackage = "api.generated";

    /**
     * Invoker Package. Optional, if needed, this can be used in templates
     */
    invokerPackage = "xfmg.openapi";

    /**
     * Invoker Package. Optional, if needed, this can be used in templates
     */
    modelPackage = "model.generated";

    /**
     * Reserved words. Override this with reserved words specific to your language
     */
    reservedWords = new HashSet<String>();

    /**
     * Additional Properties. These values can be passed to the templates and are available in
     * models, apis, and supporting files
     */
    additionalProperties.put("apiVersion", apiVersion);
    additionalProperties.put(XYNA_FACTORY_VERSION, xynaFactoryVersion);
    additionalProperties.put(XYNA_OPENAPI_VERSION, xynaOpenAPIVersion);

    /**
     * Supporting Files. You can write single files for the generator with the entire object tree
     * available. If the input file has a suffix of `.mustache it will be processed by the template
     * engine. Otherwise, it will be copied
     */

    supportingFiles.add(new SupportingFile("application.mustache", // the input template or file
        "", // the destination folder, relative `outputFolder`
        "application.xml") // the output file
    );

    /**
     * Language Specific Primitives. These types will not trigger imports by the client generator
     */
    languageSpecificPrimitives = new HashSet<String>(Arrays.asList("String", "boolean", "Boolean",
        "Double", "Integer", "Long", "Float", "OpenAPIBaseType"));

    typeMapping.clear();
    typeMapping.put("boolean", "Boolean");
    typeMapping.put("string", "String");
    typeMapping.put("int", "Integer");
    typeMapping.put("float", "Float");
    typeMapping.put("number", "Double");
    typeMapping.put("long", "Long");
    typeMapping.put("short", "Integer");
    typeMapping.put("char", "String");
    typeMapping.put("double", "Double");
    typeMapping.put("object", "Object");
    typeMapping.put("integer", "Integer");
    typeMapping.put("DateTime", "String");
    typeMapping.put("date", "String");
    typeMapping.put("file", "String");
    typeMapping.put("UUID", "String");
    typeMapping.put("ByteArray", "String");
    typeMapping.put("BigDecimal", "Double"); // should be handled internally with a String and
                                             // https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html
    typeMapping.put("binary", "String");

    cliOptions.add(new CliOption(CodegenConstants.INVOKER_PACKAGE,
        CodegenConstants.INVOKER_PACKAGE_DESC + " (Default: " + invokerPackage + ")"));
    cliOptions.add(new CliOption(CodegenConstants.API_PACKAGE,
        CodegenConstants.API_PACKAGE_DESC + " (Default: " + apiPackage + ")"));
    cliOptions.add(new CliOption(CodegenConstants.MODEL_PACKAGE,
        CodegenConstants.MODEL_PACKAGE_DESC + " (Default: " + modelPackage + ")"));
    cliOptions.add(new CliOption(PROJECT_NAME,
        "name of the project (Default: generated from info.title or \"OpenAPIXMOM\")"));
    cliOptions
        .add(new CliOption(MODULE_NAME, "module name (Default: generated from <projectName>)"));
    cliOptions.add(new CliOption(PROJECT_DESCRIPTION,
        "description of the project (Default: using info.description or \"XMOM library of <projectName>\")"));
    cliOptions.add(new CliOption(PROJECT_VERSION,
        "version of the project (Default: using info.version or \"1.0.0\")"));
    cliOptions.add(new CliOption(CodegenConstants.LICENSE_NAME,
        "name of the license the project uses (Default: using info.license.name)"));

    cliOptions.add(new CliOption(XYNA_FACTORY_VERSION,
        "version of Xyna Factory to build application for (Default: 8.2.11.14)"));
    cliOptions.add(new CliOption(XYNA_OPENAPI_VERSION,
        "version of Xyna OpenAPI App to be used as base (Default: 1.0.0)"));
  }

  @Override
  public void processOpts() {
    super.processOpts();

    if (additionalProperties.containsKey(PROJECT_NAME)) {
      setProjectName(((String) additionalProperties.get(PROJECT_NAME)));
    }
    if (additionalProperties.containsKey(MODULE_NAME)) {
      setModuleName(((String) additionalProperties.get(MODULE_NAME)));
    }
    if (additionalProperties.containsKey(PROJECT_DESCRIPTION)) {
      setProjectDescription(((String) additionalProperties.get(PROJECT_DESCRIPTION)));
    }
    if (additionalProperties.containsKey(PROJECT_VERSION)) {
      setProjectVersion(((String) additionalProperties.get(PROJECT_VERSION)));
    }
    if (additionalProperties.containsKey(CodegenConstants.LICENSE_NAME)) {
      setLicenseName(((String) additionalProperties.get(CodegenConstants.LICENSE_NAME)));
    }
    if (additionalProperties.containsKey(CodegenConstants.INVOKER_PACKAGE)) {
      setInvokerPackage((String) additionalProperties.get(CodegenConstants.INVOKER_PACKAGE));
    }
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  public void setProjectVersion(String projectVersion) {
    this.projectVersion = projectVersion;
  }

  public void setLicenseName(String licenseName) {
    this.licenseName = licenseName;
  }

  public String getInvokerPackage() {
    return invokerPackage;
  }

  public void setInvokerPackage(String invokerPackage) {
    this.invokerPackage = invokerPackage;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  @Override
  public void preprocessSwagger(Swagger swagger) {
    super.preprocessSwagger(swagger);

    if (swagger.getInfo() != null) {
      Info info = swagger.getInfo();
      if ((projectName == null || projectName.length() <= 0) && info.getTitle() != null) {
        // when projectName is not specified, generate it from info.title
        projectName = sanitizeName(underscore(info.getTitle()));
      }
      if (projectVersion == null || projectVersion.length() <= 0) {
        // when projectVersion is not specified, use info.version
        projectVersion = escapeUnsafeCharacters(escapeQuotationMark(info.getVersion()));
      }
      if (projectDescription == null) {
        // when projectDescription is not specified, use info.description
        projectDescription = sanitizeName(info.getDescription());
      }

      // when licenceName is not specified, use info.license
      if (additionalProperties.get(CodegenConstants.LICENSE_NAME) == null
          && info.getLicense() != null) {
        License license = info.getLicense();
        licenseName = license.getName();
      }
    }

    // default values
    if (projectName == null || projectName.length() <= 0) {
      projectName = "OpenAPIXMOM";
    }
    if (moduleName == null || moduleName.length() <= 0) {
      moduleName = camelize(projectName);
    }
    if (projectVersion == null || projectVersion.length() <= 0) {
      projectVersion = "1.0.0";
    }
    if (projectDescription == null) {
      projectDescription = "XMOM library of " + projectName;
    }
    if (licenseName == null || licenseName.length() <= 0) {
      licenseName = "Unlicense";
    }

    additionalProperties.put(PROJECT_NAME, projectName);
    additionalProperties.put(MODULE_NAME, moduleName);
    additionalProperties.put(PROJECT_DESCRIPTION, escapeText(projectDescription));
    additionalProperties.put(PROJECT_VERSION, projectVersion);
    additionalProperties.put(CodegenConstants.LICENSE_NAME, licenseName);
    additionalProperties.put(CodegenConstants.API_PACKAGE, apiPackage);
    additionalProperties.put(CodegenConstants.INVOKER_PACKAGE, invokerPackage);
    additionalProperties.put(CodegenConstants.MODEL_PACKAGE, modelPackage);
  }

  /**
   * Escapes a reserved word as defined in the `reservedWords` array. Handle escaping those terms
   * here. This logic is only called if a variable matches the reserved words
   * 
   * @return the escaped term
   */
  @Override
  public String escapeReservedWord(String name) {
    return "r_" + name;
  }

  /**
   * Location to write model files. You can use the modelPackage() as defined when the class is
   * instantiated
   */
  public String modelFileFolder() {
    return outputFolder + "/" + sourceFolder + "/" + invokerPackage.replace('.', File.separatorChar)
        + "/" + modelPackage().replace('.', File.separatorChar);
  }

  /**
   * Location to write api files. You can use the apiPackage() as defined when the class is
   * instantiated
   */
  @Override
  public String apiFileFolder() {
    return outputFolder + "/" + sourceFolder + "/" + invokerPackage.replace('.', File.separatorChar)
        + "/" + apiPackage().replace('.', File.separatorChar);
  }

  /**
   * Optional - type declaration. This is a String which is used by the templates to instantiate
   * your types. There is typically special handling for different property types
   *
   * @return a string value used as the `dataType` field for model templates, `returnType` for api
   *         templates
   */
  @Override
  public String getTypeDeclaration(Property p) {
    if (p instanceof MapProperty) {
      MapProperty mp = (MapProperty) p;
      Property inner = mp.getAdditionalProperties();
      return getSwaggerType(p) + "[String, " + getTypeDeclaration(inner) + "]";
    }
    return super.getTypeDeclaration(p);
  }

  /**
   * Optional - swagger type conversion. This is used to map swagger types in a `Property` into
   * either language specific types via `typeMapping` or into complex models if there is not a
   * mapping.
   *
   * @return a string value of the type or complex model for this property
   * @see io.swagger.models.properties.Property
   */
  @Override
  public String getSwaggerType(Property p) {
    String swaggerType = super.getSwaggerType(p);
    String type = null;
    if (typeMapping.containsKey(swaggerType)) {
      type = typeMapping.get(swaggerType);
      if (languageSpecificPrimitives.contains(type))
        return toModelName(type);
    } else
      type = swaggerType;
    return toModelName(type);
  }

  @Override
  public Map<String, Object> postProcessModels(Map<String, Object> objs) {
    return super.postProcessModelsEnum(objs);
  }

  @Override
  public Map<String, Object> postProcessAllModels(Map<String, Object> objs) {
    var lObjs = super.postProcessAllModels(objs);

    // Index all CodegenModels by model name.
    Map<String, CodegenModel> allModels = new HashMap<String, CodegenModel>();
    for (Entry<String, Object> entry : lObjs.entrySet()) {
      String modelName = toModelName(entry.getKey());
      Map<String, Object> inner = (Map<String, Object>) entry.getValue();
      List<Map<String, Object>> models = (List<Map<String, Object>>) inner.get("models");
      for (Map<String, Object> mo : models) {
        CodegenModel cm = (CodegenModel) mo.get("model");
        allModels.put(modelName, cm);
      }
    }

    // convert all properties pointing to an enum to String
    for (String name : allModels.keySet()) {
      CodegenModel codegenModel = allModels.get(name);
      replaceComplexTypeForEnumByString(codegenModel, allModels);
    }
    return lObjs;
  }

  private void replaceComplexTypeForEnumByString(CodegenModel codegenModel,
      Map<String, CodegenModel> allModels) {
    if (codegenModel.isEnum)
      return;

    List<CodegenProperty> vars = codegenModel.vars;

    if (codegenModel.vars != codegenModel.allVars) {
      vars.addAll(codegenModel.allVars);
    }
    for (CodegenProperty p : vars) {
      if (p.isPrimitiveType)
        continue;

      final var targetModel = allModels.get(toModelName(p.complexType));
      if (targetModel != null && targetModel.isEnum) {
        p.description = p.description + " " + "Converted to String from " + p.complexType + " Enum";
        p.unescapedDescription =
            p.unescapedDescription + " " + "Converted to String from " + p.complexType + " Enum";
        p.baseType = p.baseType.replace(p.complexType, "String");
        p.datatype = p.datatype.replace(p.complexType, "String");
        p.datatypeWithEnum = p.datatypeWithEnum.replace(p.complexType, "String");
        p.complexType = null;
        p.isString = p.isNotContainer;
        p.isPrimitiveType = p.isNotContainer;
      }
    }

  }

  @Override
  public CodegenModel fromModel(String arg0, Model arg1, Map<String, Model> arg2) {
    CodegenModel codegenModel = super.fromModel(arg0, arg1, arg2);

    // Mark all OpenAPI Objects
    if ("Object".equals(codegenModel.dataType)) {
      codegenModel.vendorExtensions.put("x-isOpenAPIBaseType", true);
    }

    return codegenModel;
  }

}
