plugins {
    id("software.onepiece.gradle.pluginshowto.java-library")
}

dependencies {
    api(project(":data-model"))
    implementation("org.apache.commons:commons-lang3:3.20.0")
}
