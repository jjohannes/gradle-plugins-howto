package software.onepiece.gradle.pluginshowto

import groovy.transform.CompileStatic
import org.gradle.api.NonNullApi
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin

@CompileStatic
@NonNullApi
abstract class MyJavaLibraryPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
    }
}
