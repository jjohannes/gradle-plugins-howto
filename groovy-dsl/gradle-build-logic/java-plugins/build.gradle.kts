plugins {
    id("groovy-gradle-plugin")
}

group = "software.onepiece.gradle.pluginshowto"

// plugin IDs are defined by the script file names: 'software.onepiece.gradle.pluginshowto.java-library.gradle' -> ID: 'software.onepiece.gradle.pluginshowto.java-library'

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.1.0") {
        because("Provides the 'com.diffplug.spotless' formatting plugin")
    }
}
