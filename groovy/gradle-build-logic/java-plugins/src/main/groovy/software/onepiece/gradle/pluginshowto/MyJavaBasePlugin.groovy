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
        project.plugins.apply(JavaPlugin)
        project.plugins.apply(SpotlessPlugin)

        // Configure Java compilation
        def java = project.extensions.getByType(JavaPluginExtension)
        java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

        // Configure JUnit5 as test framework
        project.tasks.named("test", Test) {
            it.useJUnitPlatform()
            it.testLogging.showStackTraces = true
        }
        project.dependencies.add(TEST_IMPLEMENTATION_CONFIGURATION_NAME, "org.junit.jupiter:junit-jupiter:5.7.2")

        // Configure a community plugin - example Spotless
        def spotless = project.extensions.getByType(SpotlessExtension)
        spotless.format("buildFiles") {
            it.target("build.gradle.kts")
            it.trimTrailingWhitespace()
            it.endWithNewline()
        }
    }
}
