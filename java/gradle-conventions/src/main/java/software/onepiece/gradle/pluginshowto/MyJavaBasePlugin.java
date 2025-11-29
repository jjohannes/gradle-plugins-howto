package software.onepiece.gradle.pluginshowto;

import com.diffplug.gradle.spotless.SpotlessExtension;
import com.diffplug.gradle.spotless.SpotlessPlugin;
import org.jspecify.annotations.NullMarked;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.testing.Test;
import org.gradle.jvm.toolchain.JavaLanguageVersion;

import static org.gradle.api.plugins.JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME;

@NullMarked
abstract public class MyJavaBasePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getPlugins().apply(JavaPlugin.class);
        project.getPlugins().apply(SpotlessPlugin.class);

        // Configure Java compilation
        JavaPluginExtension java = project.getExtensions().getByType(JavaPluginExtension.class);
        java.getToolchain().getLanguageVersion().set(JavaLanguageVersion.of(17));

        // Configure JUnit5 as test framework
        project.getTasks().named("test", Test.class, test -> {
            test.useJUnitPlatform();
            test.getTestLogging().setShowStandardStreams(true);
        });
        project.getDependencies().add(TEST_IMPLEMENTATION_CONFIGURATION_NAME, "org.junit.jupiter:junit-jupiter:5.7.2");

        // Configure a community plugin - example Spotless
        SpotlessExtension spotless = project.getExtensions().getByType(SpotlessExtension.class);
        spotless.format("buildFiles", format -> {
            format.target("build.gradle.kts");
            format.trimTrailingWhitespace();
            format.endWithNewline();
        });
    }
}
