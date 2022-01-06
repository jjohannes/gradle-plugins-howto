package software.onepiece.gradle.pluginshowto;

import org.gradle.api.NonNullApi;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ApplicationPlugin;

@NonNullApi
abstract public class MyJavaApplicationPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
    }
}
