plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    sourceSets {
        androidTarget()

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
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
