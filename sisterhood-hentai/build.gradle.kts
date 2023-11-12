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
                api(libs.kotlinx.serialization.json)
                api(libs.ktor.client.cio)
                api(libs.ktor.client.content.negotiation)
                api(libs.ktor.client.core)
                api(libs.ktor.serialization.kotlinx.json)
                api(projects.sisterhoodBase)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.ktor.client.android)
            }
        }
    }
}

androidWithDefault()
