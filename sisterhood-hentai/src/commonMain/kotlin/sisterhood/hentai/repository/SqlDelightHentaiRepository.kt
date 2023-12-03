package sisterhood.hentai.repository

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import kotlinx.datetime.Instant
import sisterhood.domain.*

class SqlDelightHentaiRepository(sqliteDriver: SqlDriver) : HentaiRepository {
    private val db = HentaiDatabase(
        driver = sqliteDriver,
        hentaiSpecAdapter = HentaiSpec.Adapter(
            languageAdapter = EnumColumnAdapter<HentaiLanguage>()
        )
    )

    override suspend fun insert(hentai: Hentai) = try {
        db.hentaiSpecQueries
            .insert(hentai.id, hentai.title, hentai.language, hentai.createdAt.epochNanoseconds)
    } catch (t: Throwable) {
        t.printStackTrace()
    }

    override suspend fun select(vararg ids: HentaiId): List<Hentai> = try {
        db.hentaiSpecQueries
            .select(ids.toList())
            .executeAsList()
            .map { Hentai(it.id, it.title, it.language, Instant.fromEpochNanoseconds(it.createdAt)) }
    } catch (t: Throwable) {
        t.printStackTrace()
        emptyList()
    }
}
