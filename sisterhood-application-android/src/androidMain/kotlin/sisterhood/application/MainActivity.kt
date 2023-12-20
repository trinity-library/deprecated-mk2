package sisterhood.application

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import sisterhood.application.ui.MainApp
import sisterhood.hentai.SqliteDriverFactory
import sisterhood.hentai.cache.HentaiCache
import sisterhood.hentai.infrastructure.hitomi.HitomiServiceFactory
import sisterhood.hentai.repository.HentaiDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Dependency.inject {
            configure {
                appCachePath = cacheDir.absolutePath
                appFilesPath = filesDir.absolutePath
            }

            prepare {
                cacheSchema = HentaiCache.Schema
                databaseSchema = HentaiDatabase.Schema
                sqliteDriverFactory = SqliteDriverFactory(baseContext)
                serviceFactory = HitomiServiceFactory()
            }
        }

        setContent {
            MainApp(finishApp = this::finish)
        }
    }
}
