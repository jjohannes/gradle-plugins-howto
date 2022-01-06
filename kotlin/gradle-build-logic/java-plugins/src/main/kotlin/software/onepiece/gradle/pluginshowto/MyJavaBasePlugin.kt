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
    }
}
