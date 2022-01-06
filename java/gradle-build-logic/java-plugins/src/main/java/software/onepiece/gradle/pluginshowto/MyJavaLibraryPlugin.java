package software.onepiece.gradle.pluginshowto;

import org.gradle.api.NonNullApi;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaLibraryPlugin;

@NonNullApi
abstract public class MyJavaLibraryPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getPlugins().apply(MyJavaBasePlugin.class);
        project.getPlugins().apply(JavaLibraryPlugin.class);
    }
}
