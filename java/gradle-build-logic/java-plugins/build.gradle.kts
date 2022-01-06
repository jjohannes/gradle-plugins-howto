plugins {
    id("java-gradle-plugin")
}

group = "software.onepiece.gradle.pluginshowto"

// plugins implemented as classes: we need to define IDs here
gradlePlugin {
    plugins.create("MyJavaBasePlugin") {
        id = "${project.group}.java-base"
        implementationClass = "${project.group}.${name}"
    }
    plugins.create("MyJavaLibraryPlugin") {
        id = "${project.group}.java-library"
        implementationClass = "${project.group}.${name}"
    }
    plugins.create("MyJavaApplicationPlugin") {
        id = "${project.group}.java-application"
        implementationClass = "${project.group}.${name}"
    }
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.1.0") {
        because("Provides the 'com.diffplug.spotless' formatting plugin")
    }
}
