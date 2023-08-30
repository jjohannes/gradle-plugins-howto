plugins {
    `kotlin-dsl`
    id("maven-publish") // if you never publish the plugin, you may remove this (but it also does not hurt)
    id("com.gradle.plugin-publish") version "1.2.1" // if you do not publish to the Gradle Plugin Portal, you may remove this (but it also does not hurt)
}

group = "software.onepiece.gradle.pluginshowto"

// plugin IDs are defined by the script file names: 'software.onepiece.gradle.pluginshowto.java-library.gradle.kts' -> ID: 'software.onepiece.gradle.pluginshowto.java-library'

// -- Plugin Portal Metadata (only needed if you publish to Gradle Plugin Portal) - run ':publishPlugins' task
gradlePlugin {
    website.set("https://github.com/jjohannes/gradle-plugins-howto")
    vcsUrl.set("https://github.com/jjohannes/gradle-plugins-howto.git")
    plugins.all {
        tags.set(listOf("example"))
    }
}
// ---

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.21.0") {
        because("Provides the 'com.diffplug.spotless' formatting plugin")
    }
}

// if you do not publish, the following is optional
version = "1.0"
publishing.repositories.maven("../../../_gradle-plugins-repository") // own repository to publish to (if needed) - run ':publish' task
