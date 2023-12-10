import sisterhood.androidWithDefault

plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

kotlin {
    androidTarget()

    sourceSets {
        val androidMain by getting {
            dependencies {
                api(libs.androidx.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.androidx.core.ktx)
            }
        }

        val commonMain by getting {
            dependencies {
                api(project(":sisterhood-hentai"))

                api(compose.foundation)
                api(compose.material)
                api(compose.material3)
                api(compose.preview)
                api(compose.runtime)
                api(compose.uiTooling)

                api(libs.okio)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }
    }
}

androidWithDefault()
