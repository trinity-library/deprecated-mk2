package sisterhood.hentai.infrastructure.hitomi

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import sisterhood.hentai.infrastructure.jsonColumnAdapter

class HitomiRepository(sqlDriver: SqlDriver) {
    private val db = HitomiDatabase(
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
}
