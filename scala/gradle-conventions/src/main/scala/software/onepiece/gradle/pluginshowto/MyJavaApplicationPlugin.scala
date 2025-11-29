package software.onepiece.gradle.pluginshowto

import org.gradle.api.{Plugin, Project}
import org.gradle.api.plugins.ApplicationPlugin

abstract class MyJavaApplicationPlugin extends Plugin[Project] {

    override def apply(project: Project): Unit = {
        project.getPlugins.apply(classOf[MyJavaBasePlugin])
        project.getPlugins.apply(classOf[ApplicationPlugin])
    }
}
