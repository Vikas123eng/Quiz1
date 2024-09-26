buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
   // id("org.jetbrains.kotlin.kapt")  // This is necessary for Hilt's annotation processing.
   // id("dagger.hilt.android.plugin") // Apply Hilt plugin here.
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.android.library") version "8.1.1" apply false

}