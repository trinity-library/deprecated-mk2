package sisterhood.hentai

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema

expect class SqliteDriverFactory {
    fun create(schema: SqlSchema<QueryResult.AsyncValue<Unit>>, path: String): SqlDriver
}
