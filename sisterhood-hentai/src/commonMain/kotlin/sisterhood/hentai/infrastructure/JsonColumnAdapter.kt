package sisterhood.hentai.infrastructure

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T : Any> jsonColumnAdapter() =
    object : ColumnAdapter<@Serializable T, String> {
        override fun decode(databaseValue: String): T =
            Json.decodeFromString(databaseValue)

        override fun encode(value: T): String =
            Json.encodeToString(value)
    }
