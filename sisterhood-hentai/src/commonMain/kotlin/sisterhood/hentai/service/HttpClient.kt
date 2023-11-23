package sisterhood.hentai.service

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun HttpClient.getResult(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {}
): Result<HttpResponse> =
    try {
        Result.success(get(urlString, block))
    } catch (e: ServerResponseException) {
        Result.success(e.response)
    } catch (e: Throwable) {
        Result.failure(e)
    }
