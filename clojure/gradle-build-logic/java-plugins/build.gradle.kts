plugins {
}

group = "software.onepiece.gradle.pluginshowto"

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.1.0") {
        because("Provides the 'com.diffplug.spotless' formatting plugin")
    }
}
