import sisterhood.androidWithDefault

plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}


kotlin {
    androidTarget()
    jvmToolchain(17)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.serialization.kotlinx.json)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
            }
        }
    }
}

androidWithDefault()
