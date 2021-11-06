import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.10"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    java
}

group = "me.craft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://repo.spongepowered.org/maven")
}

dependencies {
    implementation("org.testng:testng:7.1.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")

    implementation("com.github.Minestom:Minestom:master-SNAPSHOT")
    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
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

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_16.toString()
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}