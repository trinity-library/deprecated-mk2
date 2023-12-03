import sisterhood.androidWithDefault

plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.sqldelight)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}


kotlin {
    androidTarget()
    jvmToolchain(17)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.ktor.client.cio)
                api(libs.ktor.client.content.negotiation)
                api(libs.ktor.client.core)
                api(libs.ktor.serialization.kotlinx.json)
                api(libs.sqldelight.sqlite)
                api(projects.sisterhoodBase)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.ktor.client.mock)
                implementation(libs.kotest.runner.junit5)
            }
        }

        val androidMain by getting {
            dependencies {
                api(compose.runtime)
                api(libs.ktor.client.android)
                api(libs.sqldelight.android)
            }
        }

        val androidInstrumentedTest by getting {
            dependencies {
            }
        }
    }
}

androidWithDefault()

sqldelight {
    databases {
        create("HentaiCache") {
            generateAsync = true
            packageName = "sisterhood.hentai.cache"

            dialect("app.cash.sqldelight:sqlite-3-38-dialect:${libs.versions.sqldelight.get()}")
            srcDirs.setFrom("src/commonMain/sqldelight/cache")
        }

        create("HentaiDatabase") {
            generateAsync = true
            packageName = "sisterhood.hentai.repository"

            dialect("app.cash.sqldelight:sqlite-3-38-dialect:${libs.versions.sqldelight.get()}")
            srcDirs.setFrom("src/commonMain/sqldelight/database")
        }
    }
}
