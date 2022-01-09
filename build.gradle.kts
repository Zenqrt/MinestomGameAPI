import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.0"
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

    implementation("com.github.Minestom:Minestom:1e7110b")
    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
}

buildscript {
    repositories { mavenCentral() }

    dependencies {
        val kotlinVersion = "1.6.10"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}


tasks {
    named<ShadowJar>("shadowJar") {
        manifest {
            attributes (
                "Main-Class" to "dev.zenqrt.game.christmas.server.Bootstrap",
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
    freeCompilerArgs = listOf("-Xinline-classes", "-Xextended-compiler-checks")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}