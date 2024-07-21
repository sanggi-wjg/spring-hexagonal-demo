import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("io.kotest.multiplatform") version "5.5.5"
    kotlin("jvm") version "1.9.22"
    kotlin("kapt") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

allprojects {
    repositories {
        mavenCentral()
    }

    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    apply {
        plugin("idea")
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("kotlin-spring")
        plugin("kotlin-allopen")
        plugin("kotlin-jpa")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    allOpen {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.Embeddable")
        annotation("jakarta.persistence.MappedSuperclass")
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("com.ninja-squad:springmockk:4.0.2")
        testImplementation("com.tngtech.archunit:archunit:1.2.1")

        // kotest
        testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.5")
        testImplementation("io.kotest:kotest-assertions-core:5.5.5")
        testImplementation("io.kotest:kotest-property:5.5.5")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    tasks.jar {
        enabled = true
    }
    tasks.bootJar {
        enabled = false
    }
}

tasks.jar {
    enabled = true
}
tasks.bootJar {
    enabled = false
}
