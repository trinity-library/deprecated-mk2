import sisterhood.androidWithDefault

plugins {
    alias(libs.plugins.kotlin.plugin.serialization)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}


kotlin {
    sourceSets {
        androidTarget()
        jvmToolchain(17)

        val commonMain by getting {
            dependencies {
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
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.client.android)
            }
        }
    }
}

androidWithDefault()
