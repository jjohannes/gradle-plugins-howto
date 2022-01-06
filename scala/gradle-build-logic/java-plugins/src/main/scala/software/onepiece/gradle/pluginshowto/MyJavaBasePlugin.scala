package software.onepiece.gradle.pluginshowto

import com.diffplug.gradle.spotless.{FormatExtension, SpotlessExtension, SpotlessPlugin}
import org.gradle.api.plugins.JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME
import org.gradle.api.plugins.{JavaPlugin, JavaPluginExtension}
import org.gradle.api.tasks.testing.Test
import org.gradle.api.{Plugin, Project}
import org.gradle.jvm.toolchain.JavaLanguageVersion

abstract class MyJavaBasePlugin extends Plugin[Project] {

    override def apply(project: Project): Unit = {
        project.getPlugins.apply(classOf[JavaPlugin])
        project.getPlugins.apply(classOf[SpotlessPlugin])

        // Configure Java compilation
        def java = project.getExtensions.getByType(classOf[JavaPluginExtension])
        java.getToolchain.getLanguageVersion.set(JavaLanguageVersion.of(17))

        // Configure JUnit5 as test framework
        project.getTasks.named("test", classOf[Test], (test: Test) => {
            test.useJUnitPlatform()
            test.getTestLogging.setShowStandardStreams(true)
        } : Unit)
        project.getDependencies.add(TEST_IMPLEMENTATION_CONFIGURATION_NAME, "org.junit.jupiter:junit-jupiter:5.7.2")
    }
}
