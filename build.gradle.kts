import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("maven-publish")
    java
}

group = "dev.zenqrt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://repo.spongepowered.org/maven")
}

dependencies {
    implementation("org.testng:testng:7.4.0")

    testImplementation("io.kotest:kotest-assertions-core:5.0.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.0.2")

    implementation("com.github.Minestom:Minestom:1.18-SNAPSHOT")
    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
}

publishing {
    repositories {
        maven {
            name = "MinestomGameAPI"
        }
    }
}

tasks {
    named<ShadowJar>("shadowJar") {
        manifest {
            attributes (
                "Main-Class" to "dev.zenqrt.server.Bootstrap",
                "Multi-Release" to true
            )
        }

        archiveBaseName.set(project.name)
    }

    test { useJUnitPlatform() }
    build { dependsOn(shadowJar) }
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_16.toString()
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}