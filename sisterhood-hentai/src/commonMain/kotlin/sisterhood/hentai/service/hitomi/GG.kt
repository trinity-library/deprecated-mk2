package sisterhood.hentai.service.hitomi

class GG(
    private val caseNumbers: Set<Int>,
    private val bString: String,
    private val oBefore: Int,
    private val oAfter: Int
) {
    val b: String
        get() = bString

    fun m(g: Int): Int =
        if (caseNumbers.contains(g)) oAfter else oBefore

    fun s(h: String): Int =
        Regex("(..)(.)$").find(h)!!.groupValues.let { it[2] + it[1] }.toInt(16)

    companion object {
        fun fromGGJs(ggJs: String): GG {
            val caseNumbersPattern = Regex("(?<=case )[0-9]+(?=:)")
            val bStringPattern = Regex("(?<=b: ')[0-9]+/(?=')")
            val oBeforePattern = Regex("(?<=var o = )[0-1](?=;)")
            val oAfterPattern = Regex("(?<=o = )[0-1](?=; break;)")

            return GG(
                caseNumbersPattern.findAll(ggJs).map { it.value.toInt() }.toSet(),
                bStringPattern.find(ggJs)?.value ?: throw Error("Cannot find `b string`"),
                oBeforePattern.find(ggJs)?.value?.toInt() ?: throw Error("Cannot find `o before`"),
                oAfterPattern.find(ggJs)?.value?.toInt() ?: throw Error("Cannot find `o after`")
            )
        }
    }
}
