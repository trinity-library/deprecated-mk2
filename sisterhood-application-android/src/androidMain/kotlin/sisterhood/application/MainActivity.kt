package sisterhood.application

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import sisterhood.application.ui.MainApp
import sisterhood.hentai.SqliteDriverFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Dependency.inject {
            configure {
                appCachePath = cacheDir.absolutePath
                appFilesPath = filesDir.absolutePath
            }

            prepare {
                sqliteDriverFactory = SqliteDriverFactory(baseContext)
            }
        }

        setContent {
            MainApp(finishApp = this::finish)
        }
    }
}
