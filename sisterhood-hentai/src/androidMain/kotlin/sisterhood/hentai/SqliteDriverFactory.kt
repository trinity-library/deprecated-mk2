package sisterhood.hentai

import android.content.Context
import android.content.ContextWrapper
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class SqliteDriverFactory(private val context: Context) {
    actual fun create(schema: SqlSchema<QueryResult.AsyncValue<Unit>>, path: String?): SqlDriver {
        val callback = object : AndroidSqliteDriver.Callback(schema.synchronous()) {
            private fun setPragma(db: SupportSQLiteDatabase, pragma: String) {
                val cursor = db.query("PRAGMA $pragma")
                cursor.moveToFirst()
                cursor.close()
            }
        }
        
        return if (path.isNullOrEmpty()) {
            AndroidSqliteDriver(
                schema = schema.synchronous(),
                context = context,
                callback = callback
            )
        } else {
            touchFile(path)
            AndroidSqliteDriver(
                schema = schema.synchronous(),
                context = context,
                name = ContextWrapper(context).filesDir.resolve(path).absolutePath,
                callback = callback
            )
        }
    }
}
