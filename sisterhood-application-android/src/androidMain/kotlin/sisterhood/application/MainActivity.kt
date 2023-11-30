package sisterhood.application

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import sisterhood.hentai.SqliteDriverFactory
import sisterhood.hentai.cache.HentaiCache
import sisterhood.hentai.repository.HentaiDatabase
import sisterhood.hentai.service.hitomi.HitomiServiceFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Dependency.inject {
            configure {
                cachePath = ""
                databasePath = ""
            }

            prepare {
                cacheSchema = HentaiCache.Schema
                databaseSchema = HentaiDatabase.Schema
                sqliteDriverFactory = SqliteDriverFactory(baseContext)
                serviceFactory = HitomiServiceFactory()
            }
        }

        setContent {
            MainApp()
        }
    }
}
