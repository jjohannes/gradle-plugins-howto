package software.onepiece.gradle.pluginshowto

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin

abstract class MyJavaApplicationPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        plugins.apply(MyJavaBasePlugin::class.java)
        plugins.apply(ApplicationPlugin::class.java)
    }
}
