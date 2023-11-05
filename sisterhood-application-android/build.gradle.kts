plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":sisterhood-application"))
            }
        }
    }
}

android {
    compileSdk = 34
    namespace = "trinity.sisterhood"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = 30
    }
    kotlin {
        jvmToolchain(17)
    }
}
