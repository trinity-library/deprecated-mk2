plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
}


kotlin {
    sourceSets {
        androidTarget()

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
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}
