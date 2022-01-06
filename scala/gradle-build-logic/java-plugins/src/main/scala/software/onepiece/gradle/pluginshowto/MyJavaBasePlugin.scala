package software.onepiece.gradle.pluginshowto

import com.diffplug.gradle.spotless.{FormatExtension, SpotlessExtension, SpotlessPlugin}
import org.gradle.api.plugins.JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME
import org.gradle.api.plugins.{JavaPlugin, JavaPluginExtension}
import org.gradle.api.tasks.testing.Test
import org.gradle.api.{Plugin, Project}
import org.gradle.jvm.toolchain.JavaLanguageVersion

abstract class MyJavaBasePlugin extends Plugin[Project] {

    override def apply(project: Project): Unit = {
    }
}
