# Gradle Plugins: Why? How?

There is some misunderstanding and confusion about the concept of **Plugins** in Gradle.
This is unfortunate, as it is one of the central concepts of Gradle.
This README, together with the examples in this repository, aims at clarifying the most central points and questions.

For a quick introduction on the topic, you may also watch this 5min video:

[<img src="https://onepiecesoftware.github.io/img/videos/03.png" width="320">](https://www.youtube.com/watch?v=N95YI-szd78&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)

Table of contents
=================

* [FAQ](#FAQ)
* [Different Options for Writing Plugins](#different-options-for-writing-plugins)
* [Which Option should I choose?](#which-option-should-i-choose)


## FAQ

**Is writing a Gradle Plugin super complicated?**

No.

**I only use Gradle in my Java/Android application multi-project - do I need to write plugins?**

Yes. Plugins are **the** mechanism to put build/project configuration in a central place.

**Do I need to learn Kotlin or Groovy to write Gradle plugins and to configure my build?**

No. You can use Java (or Scala) if you prefer.

**What is a 'Core Plugin'?**

The term **core plugin** refers to a plugin that is part of the Gradle distribution – for example [id("java-library")](https://github.com/jjohannes/gradle-plugins-howto/blob/main/kotlin-dsl/gradle-build-logic/java-plugins/src/main/kotlin/software.onepiece.gradle.pluginshowto.java-library.gradle.kts#L3).
It is always available.

**What is a 'Community Plugin' (aka 'External Plugin' or 'Published Plugin')?**

The term **community plugin** (also called **external plugin** or **published plugin**) refers to a plugin published to the [Gradle Plugin Portal](https://plugins.gradle.org/) or another repository - for example [id("com.diffplug.spotless")](https://github.com/jjohannes/gradle-plugins-howto/blob/main/kotlin-dsl/gradle-build-logic/java-plugins/src/main/kotlin/software.onepiece.gradle.pluginshowto.java-base.gradle.kts#L3).  
To make such a plugin available so that you can use it similarly to a *core plugin*, you need to declare the repository that provides the plugin -
for example: [repositories.gradlePluginPortal()](https://github.com/jjohannes/gradle-plugins-howto/blob/main/kotlin-dsl/gradle-build-logic/settings.gradle.kts#L2).  
And you need to define a dependency to the plugin - for example:  
[implementation("com.diffplug.spotless:spotless-plugin-gradle:6.21.0")](https://github.com/jjohannes/gradle-plugins-howto/blob/main/kotlin-dsl/gradle-build-logic/java-plugins/build.gradle.kts#L12-L14).  

**What is a 'Local Plugin'?**

A **local plugin** is a plugin **you write yourself** for your own build in a _plugin build_ (aka _build logic_ or _builSrc_ build).
It is a separate Gradle build that you link to your main build by using the `includeBuild()` statement in the `pluginManagement {}` block of your settings file - for example [includeBuild("gradle-build-logic")](https://github.com/jjohannes/gradle-plugins-howto/blob/main/kotlin-dsl/settings.gradle.kts#L4).

**What is a 'Convention Plugin'?**

A **convention plugin** composes existing *core plugins* and *community plugins* and configures them with your own *conventions* - e.g. setting the Java version via [java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))](https://github.com/jjohannes/gradle-plugins-howto/blob/main/kotlin-dsl/gradle-build-logic/java-plugins/src/main/kotlin/software.onepiece.gradle.pluginshowto.java-base.gradle.kts#L7-L9).
It is a conceptual term to distinguish plugins that configure existing functionality from plugins that add new functionality (like the Android plugin).
All plugins are implemented with the same means.
That is, all implementation approaches presented below can be used for writing *convention plugins* as well as for writing plugins that add functionality (like additional tasks).
When you write *local plugins* for your own build, they are often pure convention plugins - at least initially.
Therefore, the term *convention plugin* is sometimes used to refer to what technically is a *local plugin*.

**When do I NOT need to write a plugin?**

You do not need convention plugins when your project is small and isolated so that it does not consist of multiple subprojects.
That is, you only have one `build.gradle(.kts)` script.
In this case, you can do all the build configuration in this one script and apply community plugins directly with a version (without dependency declaration) in that script - for example `id("org.jetbrains.kotlin.jvm") version "1.5.21"`.

**My projects are small, but I have many of them in a multi-repo setup - what now?**

You put your *local plugins* into a separate Git repository and use that to share build configuration among all your small builds.
You can either use the local clone of that Git repository directly via `includeBuild()`, or publish your local plugins to a Maven repository so that they become *external plugins* which you then use similarly to other *community plugins*.

**Do I need to use a specific way of implementing a Plugin to use a certain Gradle feature?**

No. In all cases, all features are accessed through a Java API. You can choose freely between the options presented below.

**I... have more questions**

Please ask by [opening an issue](https://github.com/jjohannes/gradle-plugins-howto/issues/new).

## Different Options for Writing Plugins

The choice of how to implement a plugin is purely technical and depends on your and your team's preference of language and tools.
Some considerations are listed [below](#which-option-should-i-choose).

If you intend to write a plugin for local use ony or for publishing it to share it between multiple builds in different repositories for a wider audience (community plugin) does not influence this choice.

### Choices for implementing a plugin

1. Use Gradle's Kotlin DSL - The plugin ls a `.gradle.kts` file (preferred for local plugins in most cases)
2. Use Gradle's Groovy DSL - The plugin is a `.gradle` file
3. Write a Java class (or a Scala/Kotlin/Groovy/.. class) - The plugin is an abstract class that implements the `apply(Project project)` method of the `Plugin<Project>` interface.

Technically, (1) and (2) are called _pre-compiled script plugins_ because Gradle will generate a class - similar to option (3) - from the Gradle DSL script.

### How do I do it?

This repository contains an example in **7 different flavors** using different approaches/languages.
You can download one of the examples to explore or as a starting point for your own project (via [download-directory.github.io](https://download-directory.github.io/)):

- [Plugins written in Kotlin DSL](https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2Fjjohannes%2Fgradle-plugins-howto%2Ftree%2Fmain%2Fkotlin-dsl)
- [Plugins written in Groovy DSL](https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2Fjjohannes%2Fgradle-plugins-howto%2Ftree%2Fmain%2Fgroovy-dsl)
- [Plugins written in Java      ](https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2Fjjohannes%2Fgradle-plugins-howto%2Ftree%2Fmain%2Fjava)
- [Plugins written in Kotlin    ](https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2Fjjohannes%2Fgradle-plugins-howto%2Ftree%2Fmain%2Fkotlin)
- [Plugins written in Groovy    ](https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2Fjjohannes%2Fgradle-plugins-howto%2Ftree%2Fmain%2Fgroovy)
- [Plugins written in Scala     ](https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2Fjjohannes%2Fgradle-plugins-howto%2Ftree%2Fmain%2Fscala)
- [Plugins written in Clojure   ](https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2Fjjohannes%2Fgradle-plugins-howto%2Ftree%2Fmain%2Fclojure)

Here are the typical things you need to do when writing a plugin.
They are shown here as a list of successive commits in this repository.
Each commit does the corresponding thing in all **7 approaches**.
It is **exactly the same thing** in each approach just with different syntax.
This helps to understand the differences between them.

- [Setting up the plugin project structure (same in all approaches)](https://github.com/jjohannes/gradle-plugins-howto/commit/project-structure)
- [Register your plugins (the class-based approaches need more boilerplate)](https://github.com/jjohannes/gradle-plugins-howto/commit/register-plugins)
- [Plugin writing: composing existing plugins](https://github.com/jjohannes/gradle-plugins-howto/commit/compose-plugins)
- [Plugin writing: configuring core plugins through extensions](https://github.com/jjohannes/gradle-plugins-howto/commit/configure-core-plugin)
- [Plugin writing: configuring tasks directly and adding dependencies ](https://github.com/jjohannes/gradle-plugins-howto/commit/configure-task)
- [Plugin writing: configuring community plugins through extensions](https://github.com/jjohannes/gradle-plugins-howto/commit/configure-community-plugin)
- [(Optional) Publish plugins (same in all approaches)](https://github.com/jjohannes/gradle-plugins-howto/commit/publish-plugins)

The example project does not contain any source code, but is ready for Java development.
If you open the project you downloaded with IntelliJ IDEA, you can create Java source folders and files through the IDE.

To build the project:
- Run `./gradlew build` to build the Java example project
- To publish plugins to a custom repository, run `./gradlew :gradle-build-logic:java-plugins:publish`
- To publish plugins to the plugin portal, run `./gradlew :gradle-build-logic:java-plugins:publishPlugins`

### Other resources and feedback

This repository is about explaining the different technical approaches of writing plugins.
It's not so much about how to actually configure specific things in Gradle.
It only shows examples of the most prominent things (Extensions, Task Configuration, Dependency Declaration).

- Other topics, like writing your own tasks, are covered in my [Understanding Gradle video series](https://github.com/jjohannes/understanding-gradle) - I use Kotlin DSL there for the local plugins
- My [Idiomatic Gradle repository](https://github.com/jjohannes/idiomatic-gradle) shows how to configure a larger build with local plugins (in Kotlin DSL)
- My [Gradle Demos repository](https://github.com/jjohannes/gradle-demos) has examples for doing specific things in Gradle (also mostly uses Kotlin DSL)
- If you struggle with how to do a certain thing in one of the approach, please ask by [opening an issue](https://github.com/jjohannes/gradle-plugins-howto/issues/new)

### Q&A

- [When to use another composite build or another project?](https://github.com/jjohannes/gradle-plugins-howto/issues/1)
- [Prefer to use statically compiled languages for plugins?](https://github.com/jjohannes/gradle-plugins-howto/issues/2)
- [How to create an example project and write tests for it?](https://github.com/jjohannes/gradle-plugins-howto/issues/4)

## Which Option should I choose?

Here are a few things to consider.
There is no one-fits-all right choice.

But there is the good news that all approaches can be combined.
If you split your build configuration into many small plugins, which you should do anyway for good structure and readability, you can use a different approach for each plugin.
Thus, if at some point you want to migrate from one approach to another, you can do it step-by-step.

Here is a non-exhaustive list of aspects you may consider when making the choice.

### Conceptual considerations

- **General readability and compactness:**
  The Kotlin DSL and Groovy DSL solutions are more compact and thus generally simpler to read and to maintain.
  That's why Gradle provides them in the first place. However, other factors below might overrule this advantage.
- **IDE Support:**
  If you use IntelliJ IDEA, the support for Kotlin DSL is quite good nowadays.
  You get code completion, code navigation, and proper warnings in your plugin scripts.
  So if you use IDEA and have no other preference, due to the compactness argument above, **Kotlin DSL is often the best choice**.
  If you mainly use Eclipse for example, you probably don't do Kotlin development as Kotlin is not well-supported.
  Then another language might be the better choice from the tooling perspective.
- **Language preference, experience and languages use in other parts of the Project (now and future):**
  If the build configuration looks alien to most developers in a team, chances are high that it ends up unmaintained at some point leading to more trouble later.
  If the configuration is performed in a familiar language, getting into it is usually easier for developers not familiar with Gradle.
  So if your work, for example, on a pure Java (Scala) project, doing everything in Java (Scala) might be a better choice despite some more verbose syntax.
  This not only concerns the current state of the project but also the future.
  E.g. if there are plans to gain Kotlin knowledge to do Kotlin development in the future, you might as well start with Kotlin DSL for your build configuration.

### Technical/performance considerations and other caveats

- **Kotlin DSL compilation time:**
  Although conceptually Kotlin DSL is the best choice most of the time, there is a technical drawback.
  The compilation of Kotlin code and scripts is slower compared to pure Java or Groovy.
  However, you will only notice this with a larger amount of build configuration.
  Furthermore, the results of the compilation are cached. The issue only manifests when you change build configuration.
  Builds that you run after the scripts have been compiled already, are not affected by this drawback.
  Both JetBrains and Gradle are working on improving this and the disadvantage will probably go away long term.
  If this is an issue for you anyway, but you still want to use a DSL, you could consider the Groovy DSL instead.
- **Kotlin runtime (standard library) version is fixed by Gradle:**
  If you use Kotlin DSL or Kotlin for your plugins, you need to be aware that it will run against the Kotlin version packaged with Gradle.
  If you use `kotlin-dsl` in plugin development, you will automatically develop against the Kotlin version packaged with the Gradle distribution you are using.
  This is only a concern if you publish plugins publicly (e.g. to the Plugin Portal) that are used with multiple Gradle versions.
  You do not have this concern when developing plugins in plain Java. 
- **Groovy runtime (standard library) version is fixed by Gradle:**
  The issue described above also applies for plugins developed in Groovy DSL or Groovy.
- **Other runtimes/dependencies:**
  If your plugin has dependencies, for example the Scala Library, it can only be combined with other plugins that have the same dependencies if all plugins work with compatible versions of the dependencies.
  Gradle loads all the plugins and their dependencies together because they may interact with each other.
  Thus, if there are dependency conflicts, Gradle's dependency resolution will resolve them and select only **one** version of a dependency.
  This is only a concern if you publish plugins publicly (e.g. to the Plugin Portal) where you do not know with which other plugins they may be combined.
  This is a general concern to be aware of with all approaches.
  But it's specific if the language you use forces you to have a certain dependency (Scala and Clojure in the examples).
  If you develop a community plugin that you share, it is preferred to use a language that Gradle can run without additional dependencies (Java, Kotlin or Groovy).
