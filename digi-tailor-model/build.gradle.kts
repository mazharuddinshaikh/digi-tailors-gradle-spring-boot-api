/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.10.1/samples
 */
plugins {
    application
}

group = "in.voter.digi"
version = "0.0.2-SNAPSHOT"
java {
    toolchain {
        var languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}
dependencies {
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
//	jackson library for json data
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")

}