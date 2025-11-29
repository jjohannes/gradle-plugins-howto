pluginManagement {
    repositories.gradlePluginPortal()
 
    includeBuild("gradle-conventions")

    // To get your plugins from a repository instead of 'gradle-conventions', remove in includeBuild above and instead do:
    // repositories.maven("../_gradle-plugins-repository")
    // plugins {
    //     id("software.onepiece.gradle.pluginshowto.java-library") version "1.0"
    //     id("software.onepiece.gradle.pluginshowto.java-application") version "1.0"
    // }
}

dependencyResolutionManagement {
    repositories.mavenCentral()
}

include("app")
include("business-logic")
include("data-model")
