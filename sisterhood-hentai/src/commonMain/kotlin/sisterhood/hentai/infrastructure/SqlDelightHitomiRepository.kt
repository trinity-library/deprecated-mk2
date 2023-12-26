package sisterhood.hentai.infrastructure

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import kotlinx.serialization.json.Json
import sisterhood.domain.HentaiId
import sisterhood.hentai.business.HitomiRepository
import sisterhood.hentai.domain.hitomi.HitomiGallery

class SqlDelightHitomiRepository(sqlDriver: SqlDriver) : HitomiRepository {
    private val db = HentaiDatabase(
        driver = sqlDriver,
        hitomiArtistSpecAdapter = HitomiArtistSpec.Adapter(jsonColumnAdapter()),
        hitomiCharacterSpecAdapter = HitomiCharacterSpec.Adapter(jsonColumnAdapter()),
        hitomiGallerySpecAdapter = HitomiGallerySpec.Adapter(
            languageAdapter = EnumColumnAdapter(),
            filesAdapter = jsonColumnAdapter(),
            relatedAdapter = jsonColumnAdapter()
        ),
        hitomiGroupSpecAdapter = HitomiGroupSpec.Adapter(jsonColumnAdapter()),
        hitomiParodySpecAdapter = HitomiParodySpec.Adapter(jsonColumnAdapter()),
        hitomiTagSpecAdapter = HitomiTagSpec.Adapter(jsonColumnAdapter())
    )

    override suspend fun insertGallery(gallery: HitomiGallery) = try {
        db.transaction {
            with(gallery) {
                with(db.hitomiGallerySpecQueries) {
                    this.insertGallery(id, title, language, files, date, related)
                    artists.forEach { artist -> insertArtist(id, artist) }
                    characters.forEach { character -> insertCharacter(id, character) }
                    groups.forEach { group -> insertGroup(id, group) }
                    parodys.forEach { parody -> insertParody(id, parody) }
                    tags.forEach { tag -> insertTag(id, tag) }
                }
            }
        }
    } catch (t: Throwable) {
        t.printStackTrace()
    }

    override suspend fun selectGallery(id: HentaiId): HitomiGallery? = try {
        db.hitomiGallerySpecQueries.selectGallery(id).executeAsOneOrNull()?.let {
            HitomiGallery(
                id = it.id,
                title = it.title,
                language = it.language,
                files = it.files,
                date = it.date,
                artists = it.artists?.split(";")?.map { Json.decodeFromString(it) } ?: emptyList(),
                characters = it.characters?.split(";")?.map { Json.decodeFromString(it) } ?: emptyList(),
                groups = it.groups?.split(";")?.map { Json.decodeFromString(it) } ?: emptyList(),
                parodys = it.parodys?.split(";")?.map { Json.decodeFromString(it) } ?: emptyList(),
                related = it.related,
                tags = it.tags?.split(";")?.map { Json.decodeFromString(it) } ?: emptyList()
            )
        }
    } catch (t: Throwable) {
        t.printStackTrace()
        null
    }
}
