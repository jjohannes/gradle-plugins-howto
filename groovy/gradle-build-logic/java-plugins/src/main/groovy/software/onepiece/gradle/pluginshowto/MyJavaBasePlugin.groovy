package software.onepiece.gradle.pluginshowto

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import groovy.transform.CompileStatic
import org.gradle.api.NonNullApi
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.toolchain.JavaLanguageVersion

import static org.gradle.api.plugins.JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME

@CompileStatic
@NonNullApi
abstract class MyJavaBasePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
    }
}
