package sisterhood.hentai.repository

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import kotlinx.datetime.Instant
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import sisterhood.domain.*

class SqlDelightHentaiRepository(sqliteDriver: SqlDriver) : HentaiRepository {
    private val listOfHentaiPageAdapter = object : ColumnAdapter<List<HentaiPage>, String> {
        override fun decode(databaseValue: String): List<HentaiPage> = Json.decodeFromString(databaseValue)
        override fun encode(value: List<HentaiPage>) = Json.encodeToString(value)
    }

    private val db = HentaiDatabase(
        driver = sqliteDriver,
        hentaiSpecAdapter = HentaiSpec.Adapter(
            languageAdapter = EnumColumnAdapter<HentaiLanguage>(),
            pagesAdapter = listOfHentaiPageAdapter
        )
    )

    override suspend fun insert(hentai: Hentai) = try {
        db.hentaiSpecQueries
            .insert(hentai.id, hentai.title, hentai.language, hentai.pages, hentai.createdAt.epochNanoseconds)
    } catch (t: Throwable) {
        t.printStackTrace()
    }

    override suspend fun select(vararg ids: HentaiId): List<Hentai> = try {
        db.hentaiSpecQueries
            .select(ids.toList())
            .executeAsList()
            .map { Hentai(it.id, it.title, it.language, it.pages, Instant.fromEpochNanoseconds(it.createdAt)) }
    } catch (t: Throwable) {
        t.printStackTrace()
        emptyList()
    }
}
