package software.onepiece.gradle.pluginshowto

import groovy.transform.CompileStatic
import org.gradle.api.NonNullApi
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin

@CompileStatic
@NonNullApi
abstract class MyJavaApplicationPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.plugins.apply(MyJavaBasePlugin)
        project.plugins.apply(ApplicationPlugin)
    }
}
