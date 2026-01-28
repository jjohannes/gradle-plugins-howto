plugins {
    id("java-gradle-plugin")
    id("maven-publish") // if you never publish the plugin, you may remove this (but it also does not hurt)
    id("com.gradle.plugin-publish") version "2.0.0" // if you do not publish to the Gradle Plugin Portal, you may remove this (but it also does not hurt)
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
    // -- Plugin Portal Metadata (only needed if you publish to Gradle Plugin Portal) - run ':publishPlugins' task
    website.set("https://github.com/jjohannes/gradle-plugins-howto")
    vcsUrl.set("https://github.com/jjohannes/gradle-plugins-howto.git")
    plugins.all {
        tags.set(listOf("example"))
    }
    // ---
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:8.2.1") {
        because("Provides the 'com.diffplug.spotless' formatting plugin")
    }
}

// if you do not publish, the following is optional
version = "1.0"
publishing.repositories.maven("../../_gradle-plugins-repository") // own repository to publish to (if needed) - run ':publish' task
