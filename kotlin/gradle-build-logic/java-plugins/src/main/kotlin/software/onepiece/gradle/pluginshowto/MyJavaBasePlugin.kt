package software.onepiece.gradle.pluginshowto

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.toolchain.JavaLanguageVersion

abstract class MyJavaBasePlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        plugins.apply(JavaPlugin::class.java)
        plugins.apply(SpotlessPlugin::class.java)

        // Configure Java compilation
        val java = extensions.getByType(JavaPluginExtension::class.java)
        java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

        // Configure JUnit5 as test framework
        tasks.named("test", Test::class.java) {
            it.useJUnitPlatform()
            it.testLogging.showStackTraces = true
        }
        dependencies.add(TEST_IMPLEMENTATION_CONFIGURATION_NAME, "org.junit.jupiter:junit-jupiter:5.7.2")

        // Configure a community plugin - example Spotless
        val spotless = extensions.getByType(SpotlessExtension::class.java)
        spotless.format("buildFiles") {
            it.target("build.gradle.kts")
            it.trimTrailingWhitespace()
            it.endWithNewline()
        }
    }
}
