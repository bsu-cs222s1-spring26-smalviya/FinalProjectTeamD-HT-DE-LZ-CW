plugins {
    id("java")
    //OpenAPIGenerator for USDA
    id("org.openapi.generator") version "7.21.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // --- Json for OpenAPI Generated Code ---
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.15")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    //Json
    implementation(group = "org.json", name = "json", version = "20090211")
}
//Generates OPENAPI for java
openApiGenerate {
    generatorName.set("java")
    val apiKey = project.findProperty("usdaKey")?.toString() ?: "DEMO_KEY"
    // This will print the API key you're using to be sure
    //println("--- GENERATING USDA CLIENT USING KEY: $apiKey ---")
    inputSpec.set("https://api.nal.usda.gov/fdc/v1/json-spec?api_key=$apiKey")
    // ... rest of config
    outputDir.set(layout.buildDirectory.dir("generated").get().asFile.absolutePath)

    apiPackage.set("com.usda.api")
    modelPackage.set("com.usda.model")
    invokerPackage.set("com.usda.invoker")

    configOptions.set(mapOf(
        "dateLibrary" to "java8",
        "library" to "native", // Uses Java's built-in HttpClient (Java 11+)
        "serializationLibrary" to "jackson"
    ))
}
//Tells gradle where the generated code is so IDE finds the code generated above
sourceSets {
    main {
        java {
            srcDir(layout.buildDirectory.dir("generated/src/main/java"))
        }
    }
}
//Forces the code above to generate before the other classes.
tasks.compileJava {
    dependsOn(tasks.openApiGenerate)
}

tasks.test {
    useJUnitPlatform()
}