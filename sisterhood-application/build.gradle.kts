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
                api(compose.material)
                api(compose.preview)
                api(compose.runtime)
                api(compose.uiTooling)
                api(project(":sisterhood-hentai"))
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
