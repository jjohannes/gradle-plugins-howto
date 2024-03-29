(ns software.onepiece.gradle.pluginshowto.MyJavaBasePlugin
    (:import
      (com.diffplug.gradle.spotless SpotlessPlugin SpotlessExtension)
      (org.gradle.api Project Plugin, Action)
      (org.gradle.api.plugins JavaPlugin JavaPluginExtension)
      (org.gradle.api.tasks.testing Test)
      (org.gradle.jvm.toolchain JavaLanguageVersion)
    )
    (:gen-class
      :name       software.onepiece.gradle.pluginshowto.MyJavaBasePlugin
      :implements [org.gradle.api.Plugin])
    )

(defn -apply [this ^Project project]
  (do
    (.apply (.getPlugins project) JavaPlugin)
    (.apply (.getPlugins project) SpotlessPlugin)

    ; Configure Java compilation
    (.set (.getLanguageVersion (.getToolchain (.getByType (.getExtensions project) JavaPluginExtension))) (JavaLanguageVersion/of 17))

    ; Configure JUnit5 as test framework
    (.named (.getTasks project) "test" (reify Action (execute [this test] (do
      (.useJUnitPlatform test)
      (.showStackTraces (.getTestLogging test) true)
    ))))
    (.add (.getDependencies project) "implementation" "org.junit.jupiter:junit-jupiter:5.7.2")

    ; Configure a community plugin - example Spotless
    (.format (.getByType (.getExtensions project) SpotlessExtension) "buildFiles" (reify Action (execute [this format] (do
      (.target format (into-array Object ["build.gradle.kts"]))
      (.trimTrailingWhitespace format)
      (.endWithNewline format)
    ))))
  )
)