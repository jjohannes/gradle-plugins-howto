(ns software.onepiece.gradle.pluginshowto.MyJavaApplicationPlugin
    (:import
      (org.gradle.api Project Plugin)
      (org.gradle.api.plugins ApplicationPlugin)
    )
    (:gen-class
      :name       software.onepiece.gradle.pluginshowto.MyJavaApplicationPlugin
      :implements [org.gradle.api.Plugin])
    )

(defn -apply [this ^org.gradle.api.Project project]
  (do
    (.apply (.getPlugins project) "software.onepiece.gradle.pluginshowto.java-base")
    (.apply (.getPlugins project) ApplicationPlugin)
  )
)
