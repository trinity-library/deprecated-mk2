package sisterhood.hentai.repository

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import kotlinx.datetime.Instant
import sisterhood.*

class SqlDelightHentaiRepository(sqliteDriver: SqlDriver) : HentaiRepository {
    private val db = HentaiDatabase(
        driver = sqliteDriver,
        hentaiSpecAdapter = HentaiSpec.Adapter(
            languageAdapter = EnumColumnAdapter<HentaiLanguage>()
        )
    )

    override suspend fun insert(hentai: Hentai) =
        db.hentaiSpecQueries
            .insert(hentai.id.toLong(), hentai.title, hentai.language, hentai.createdAt.epochNanoseconds)

    override suspend fun select(vararg ids: HentaiId): List<Hentai> =
        db.hentaiSpecQueries
            .select(ids.toList())
            .executeAsList()
            .map { Hentai(it.id, it.title, it.language, Instant.fromEpochNanoseconds(it.createdAt)) }
}
