plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.7"
    id("xyz.jpenilla.run-paper") version "2.3.0"
    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = findProperty("group")!! as String
version = findProperty("version")!! as String
val slug = findProperty("slug")!! as String
description = findProperty("description")!! as String

repositories {
    mavenLocal()
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.sayandev.org/snapshots")
}

dependencies {
    paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")

    implementation("org.sayandev:stickynote-bukkit:1.0.38")
}

tasks {
    java {
        disableAutoTargetJvm()
        toolchain.languageVersion = JavaLanguageVersion.of(17)

        if (gradle.startParameter.getTaskNames().isNotEmpty() && gradle.startParameter.getTaskNames().contains("runServer")) {
            toolchain.languageVersion = JavaLanguageVersion.of(21)
        }


        manifest {
            attributes["paperweight-mappings-namespace"] = "spigot"
        }
    }

    processResources {
        filesMatching(listOf("**plugin.yml", "**plugin.json")) {
            expand(
                "version" to rootProject.version,
                "slug" to slug,
                "name" to rootProject.name,
                "description" to rootProject.description
            )
        }
    }

    runServer {
        minecraftVersion("1.20.6")
    }

    shadowJar {
        relocate("net.kyori", "org.sayandev.adventuretest.lib.net.kyori.adventure")

        manifest {
            attributes["paperweight-mappings-namespace"] = "spigot"
        }
    }

    paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.REOBF_PRODUCTION
}

bukkit {
    name = rootProject.name
    version = rootProject.version as String
    description = rootProject.description
    website = findProperty("website")!! as String
    author = findProperty("author")!! as String

    main = "${rootProject.group}.${rootProject.name}"

    apiVersion = "1.13"
}
