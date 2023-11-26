package sisterhood.hentai

import java.io.File
import java.io.FileOutputStream

fun touchFile(path: String) {
    val file = File(path)
    if (!file.exists()) {
        FileOutputStream(file).close();
    }
    file.setLastModified(System.currentTimeMillis())
}
