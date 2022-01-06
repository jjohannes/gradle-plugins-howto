plugins {
    id("java")
    id("com.diffplug.spotless")
}

// Configure Java compilation
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
