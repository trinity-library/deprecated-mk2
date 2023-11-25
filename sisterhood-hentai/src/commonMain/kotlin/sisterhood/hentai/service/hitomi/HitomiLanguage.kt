package sisterhood.hentai.service.hitomi

import sisterhood.HentaiLanguage

enum class HitomiLanguage {
    ENGLISH,
    JAPANESE,
    KOREAN,
    ALBANIAN,
    ARABIC,
    BULGARIAN,
    BURMESE,
    CATALAN,
    CEBUANO,
    CHESKEY,
    DANISH,
    DUTCH,
    ESPERANTO,
    ESTONIAN,
    FINNISH,
    FRENCH,
    GERMAN,
    GREEK,
    HEBREW,
    HINDI,
    HUNGARIAN,
    ICELANDIC,
    INDONESIAN,
    ITALIAN,
    LATIN,
    JAVANESE,
    MONGOLIAN,
    NORWEGIAN,
    PERSIAN,
    POLISH,
    PORTUGUESE,
    ROMANIAN,
    RUSSIAN,
    SERBIAN,
    SLOVAK,
    SPANISH,
    SWEDISH,
    TAGALOG,
    THAI,
    TURKISH,
    UKRAINIAN,
    VIETNAMESE,
    NONE;

    companion object {
        fun from(hentaiLanguage: HentaiLanguage) =
            try {
                valueOf(hentaiLanguage.name)
            } catch (_: IllegalAccessException) {
                NONE
            }
    }

    fun toHentaiLanguage(): HentaiLanguage =
        try {
            HentaiLanguage.valueOf(this.name)
        } catch (_: IllegalAccessException) {
            HentaiLanguage.UNSUPPORTED
        }
}
