import build.less.plugin.gradle.ApiKey
import build.less.plugin.gradle.MutableAgentCacheSettings
import java.util.Properties

rootProject.name = "FancyApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("build.less") version "1.0.0-rc2"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":composeApp")

buildless {
    val properties = Properties().apply {
        rootProject.projectDir.resolve(relative = "local.properties").inputStream().use {
            load(it)
        }
    }
    val apiKey = properties.getProperty("buildless.user.key")
        ?: throw Exception("Need Buildless key!")
    apiKey(ApiKey.forUser(apiKey))
}
