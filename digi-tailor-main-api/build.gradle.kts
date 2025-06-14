/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/8.1.1/samples
 */
plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "in.tailor.digi"
version = "0.0.1-SNAPSHOT"
extra["springCloudVersion"] = "2023.0.3"

java {
    toolchain {
        var languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":digi-tailor-model"))
    implementation(project(":digi-tailor-util"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    runtimeOnly("com.mysql:mysql-connector-j")
    compileOnly("org.projectlombok:lombok:1.18.34")
    compileOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    modules {
        module("org.springframework.boot:spring-boot-starter-logging") {
            replacedBy("org.springframework.boot:spring-boot-starter-log4j2", "Use Log4j2 instead of Logback")
        }
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}