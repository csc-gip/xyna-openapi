# Swagger Codegen for the XynaXMOM library

v2
https://github.com/swagger-api/swagger-codegen-generators/wiki/Adding-a-new-generator-for-a-language-or-framework.
https://github.com/swagger-api/swagger-codegen-generators/pull/451/commits/2f53b03cd61003d4b5d6505cd8cc5da855a638d6#diff-16375f2279f5f42ab8817fb15f8827f2cda3711ff3cffdbfcf6d001ae31049bf

v3
https://github.com/swagger-api/swagger-codegen/blob/3.0.0/standalone-gen-dev/standalone-generator-development.md


Und es gibt einen Fork: https://openapi-generator.tech/docs/fork-qna/
Der kann aber keinen Standalone Generator, so wie es aussieht.

## Overview
This is a boiler-plate project to generate your own client library with Swagger.  Its goal is
to get you started with the basic plumbing so you can put in your own logic.  It won't work without
your changes applied.

## What's Swagger?
The goal of Swagger™ is to define a standard, language-agnostic interface to REST APIs which allows both humans and computers to discover and understand the capabilities of the service without access to source code, documentation, or through network traffic inspection. When properly defined via Swagger, a consumer can understand and interact with the remote service with a minimal amount of implementation logic. Similar to what interfaces have done for lower-level programming, Swagger removes the guesswork in calling the service.


Check out [OpenAPI-Spec](https://github.com/OAI/OpenAPI-Specification) for additional information about the Swagger project, including additional libraries with support for other languages and more. 

## How do I use this?
At this point, you've likely generated a client setup.  It will include something along these lines:

```
.
|- README.md    // this file
|- pom.xml      // build script
|-- src
|--- main
|---- java
|----- com.gip.xyna.openapi.codegen.XynaxXMOMenerator.java // generator file
|---- resources
|----- mustache/XynaXMOM // template files
|----- META-INF
|------ services
|------- io.swagger.codegen.v3.CodegenConfig
```

You _will_ need to make changes in at least the following:

`XynaXMOMGenerator.java`

Templates in this folder:

`src/main/resources/mustache/XynaXMOM`

Once modified, you can run this:

```
mvn package
```

In your generator project.  A single jar file will be produced in `target`.  You can now use that with codegen:

```
java -cp /path/to/swagger-codegen-cli.jar:/path/to/your.jar io.swagger.codegen.v3.cli.SwaggerCodegen generate -l XynaXMOM --template-engine mustache -i /path/to/swagger.yaml -o ./test
```

```
wget https://github.com/swagger-api/swagger-codegen/raw/3.0.0/modules/swagger-codegen/src/test/resources/3_0_0/petstore.json 
java -DdebugModels -cp "target/XynaXMOM-openapi-codegen-1.0.0.jar;swagger-codegen-cli-3.0.21.jar" io.swagger.codegen.v3.cli.SwaggerCodegen generate -l XynaXMOM --template-engine mustache -i petstore.json -o xmom > out.txt
```

Now your templates are available to the client generator and you can write output values

## But how do I modify this?
The `XynaxmomGenerator.java` has comments in it--lots of comments.  There is no good substitute
for reading the code more, though.  See how the `XynaxmomGenerator` implements `CodegenConfig`.
That class has the signature of all values that can be overridden.

For the templates themselves, you have a number of values available to you for generation.
You can execute the `java` command from above while passing different debug flags to show
the object you have available during client generation:

```
# The following additional debug options are available for all codegen targets:
# -DdebugSwagger prints the OpenAPI Specification as interpreted by the codegen
# -DdebugModels prints models passed to the template engine
# -DdebugOperations prints operations passed to the template engine
# -DdebugSupportingFiles prints additional data passed to the template engine

java -DdebugOperations -cp /path/to/swagger-codegen-cli.jar:/path/to/your.jar io.swagger.codegen.v3.cli.SwaggerCodegen generate -l XynaXMOM --template-engine mustache -i /path/to/swagger.yaml -o ./test
```

Will, for example, output the debug info for operations.  You can use this info
in the `api.mustache` file.