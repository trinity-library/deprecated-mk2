plugins {
    alias(libs.plugins.kotlin.plugin.serialization)
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

kotlin {
    jvm()
    jvmToolchain(17)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.datetime)
                api(libs.kotlinx.serialization.json)
            }
        }
    }
}
