import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    id("net.minecraftforge.gradle") version "5.1.+"
}

group = "io.github.plakezz.ghostpro"
version = "1.0"
archivesBaseName = "ghostpro"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8)) // Java 8
}

minecraft {
    version = "1.8.9-11.15.1.2318-1.8.9"
    runDir = "run"
    mappings = "stable_20"

    // ForgeGradle 5.1 spezifische Einstellungen
    accessTransformer = file("src/main/resources/META-INF/accesstransformer.cfg")

    // Füge hier ggf. weitere Forge-spezifische Konfigurationen hinzu
}

repositories {
    mavenCentral()
    maven("https://repo.hypixel.net/repository/Hypixel/")
}

dependencies {
    minecraft("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9") // Forge als Abhängigkeit
    implementation("net.hypixel:hypixel-api-transport-apache:4.4")
    // Weitere Abhängigkeiten, falls benötigt, hier hinzufügen
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

processResources {
    inputs.property("version", project.version)
    inputs.property("mcversion", project.minecraft.version)

    from(sourceSets.main.resources.srcDirs) {
        include "mcmod.info"
        expand("version" to project.version, "mcversion" to project.minecraft.version)
    }

    from(sourceSets.main.resources.srcDirs) {
        exclude "mcmod.info"
    }
}


