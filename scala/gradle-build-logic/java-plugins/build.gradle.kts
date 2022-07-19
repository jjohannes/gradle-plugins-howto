plugins {
    id("java-gradle-plugin")
    id("scala")
    id("maven-publish") // if you never publish the plugin, you may remove this (but it also does not hurt)
    id("com.gradle.plugin-publish") version "1.0.0" // if you do not publish to the Gradle Plugin Portal, you may remove this (but it also does not hurt)
}

dependencies { implementation("org.scala-lang:scala-library:2.13.8") }

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

// if you do not publish, the following is optional
version = "1.0"
publishing.repositories.maven("../../../_gradle-plugins-repository") // own repository to publish to (if needed) - run ':publish' task
pluginBundle { // Plugin Portal Metadata (only needed if you publish to Gradle Plugin Portal) - run ':publishPlugins' task
    website = "https://github.com/jjohannes/gradle-plugins-howto"
    vcsUrl = "https://github.com/jjohannes/gradle-plugins-howto.git"
    tags = listOf("example")
}
