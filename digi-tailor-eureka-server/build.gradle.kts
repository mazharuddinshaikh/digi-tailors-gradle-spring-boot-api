/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/8.10.1/samples
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

repositories {
	mavenCentral()
}
dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	modules {
        module("org.springframework.boot:spring-boot-starter-logging") {
            replacedBy("org.springframework.boot:spring-boot-starter-log4j2", "Use Log4j2 instead of Logback")
        }
    }
}

tasks.withType<Test> {
	useJUnitPlatform()
}