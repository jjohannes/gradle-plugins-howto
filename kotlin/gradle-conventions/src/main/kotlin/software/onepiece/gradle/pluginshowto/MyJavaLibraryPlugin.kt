package software.onepiece.gradle.pluginshowto

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin

abstract class MyJavaLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        plugins.apply(MyJavaBasePlugin::class.java)
        plugins.apply(JavaLibraryPlugin::class.java)
    }
}
