import sisterhood.androidWithDefault

plugins {
    alias(libs.plugins.compose)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

kotlin {
    androidTarget()
    jvmToolchain(17)

    sourceSets {
        val androidMain by getting {
            dependencies {
                api(compose.material)
                api(compose.preview)
                api(compose.runtime)
                api(compose.uiTooling)
                api(libs.androidx.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.androidx.core.ktx)
            }
        }
    }
}

androidWithDefault()
