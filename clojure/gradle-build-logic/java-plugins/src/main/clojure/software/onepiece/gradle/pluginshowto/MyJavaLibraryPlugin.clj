(ns software.onepiece.gradle.pluginshowto.MyJavaLibraryPlugin
    (:import
      (org.gradle.api Project Plugin)
      (org.gradle.api.plugins JavaLibraryPlugin)
    )
    (:gen-class
      :name       software.onepiece.gradle.pluginshowto.MyJavaLibraryPlugin
      :implements [org.gradle.api.Plugin]
    )
  )

(defn -apply [this ^Project project]
  (do
  )
)

