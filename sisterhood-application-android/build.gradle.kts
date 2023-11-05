import sisterhood.androidCompileSdk
import sisterhood.androidMinSdk
import sisterhood.androidNamespace

plugins {
    alias(libs.plugins.jetbrains.compose)
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

kotlin {
    androidTarget()
    jvmToolchain(17)

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":sisterhood-application"))
            }
        }
    }
}

android {
    compileSdk = androidCompileSdk
    namespace = "$androidNamespace.android"

    defaultConfig {
        minSdk = androidMinSdk
    }
}
