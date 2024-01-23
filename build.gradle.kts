buildscript {
}// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version libs.versions.android.gradle.get() apply false
    id("com.android.library") version libs.versions.android.gradle.get() apply false
    id("org.jetbrains.kotlin.android") version libs.versions.kotlin.get() apply false
    id("com.squareup.anvil") version libs.versions.anvil.get() apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory.get())
}