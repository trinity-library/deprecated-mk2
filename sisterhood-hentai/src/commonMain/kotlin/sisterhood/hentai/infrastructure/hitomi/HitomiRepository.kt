package sisterhood.hentai.infrastructure.hitomi

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import sisterhood.hentai.domain.hitomi.*

class HitomiRepository(sqlDriver: SqlDriver) {
    private val db = HitomiDatabase(
        driver = sqlDriver,
        hitomiArtistSpecAdapter = HitomiArtistSpec.Adapter(hitomiArtistAdapter),
        hitomiCharacterSpecAdapter = HitomiCharacterSpec.Adapter(hitomiCharacterAdapter),
        hitomiGallerySpecAdapter = HitomiGallerySpec.Adapter(
            languageAdapter = EnumColumnAdapter(),
            filesAdapter = listOfHitomiFileAdapter,
            relatedAdapter = listOfIntAdapter
        ),
        hitomiGroupSpecAdapter = HitomiGroupSpec.Adapter(hitomiGroupAdapter),
        hitomiParodySpecAdapter = HitomiParodySpec.Adapter(hitomiParodyAdapter),
        hitomiTagSpecAdapter = HitomiTagSpec.Adapter(hitomiTagAdapter)
    )

    companion object {
        val hitomiArtistAdapter = object : ColumnAdapter<HitomiArtist, String> {
            override fun decode(databaseValue: String): HitomiArtist =
                Json.decodeFromString(databaseValue)

            override fun encode(value: HitomiArtist): String =
                Json.encodeToString(value)
        }

        val hitomiCharacterAdapter = object : ColumnAdapter<HitomiCharacter, String> {
            override fun decode(databaseValue: String): HitomiCharacter =
                Json.decodeFromString(databaseValue)

            override fun encode(value: HitomiCharacter): String =
                Json.encodeToString(value)
        }

        val listOfHitomiFileAdapter = object : ColumnAdapter<List<HitomiFile>, String> {
            override fun decode(databaseValue: String): List<HitomiFile> =
                Json.decodeFromString(databaseValue)

            override fun encode(value: List<HitomiFile>): String =
                Json.encodeToString(value)
        }

        val listOfIntAdapter = object : ColumnAdapter<List<Int>, String> {
            override fun decode(databaseValue: String): List<Int> =
                Json.decodeFromString(databaseValue)

            override fun encode(value: List<Int>): String =
                Json.encodeToString(value)
        }

        val hitomiGroupAdapter = object : ColumnAdapter<HitomiGroup, String> {
            override fun decode(databaseValue: String): HitomiGroup =
                Json.decodeFromString(databaseValue)

            override fun encode(value: HitomiGroup): String =
                Json.encodeToString(value)
        }

        val hitomiParodyAdapter = object : ColumnAdapter<HitomiParody, String> {
            override fun decode(databaseValue: String): HitomiParody =
                Json.decodeFromString(databaseValue)

            override fun encode(value: HitomiParody): String =
                Json.encodeToString(value)
        }

        val hitomiTagAdapter = object : ColumnAdapter<HitomiTag, String> {
            override fun decode(databaseValue: String): HitomiTag =
                Json.decodeFromString(databaseValue)

            override fun encode(value: HitomiTag): String =
                Json.encodeToString(value)
        }
    }
}
