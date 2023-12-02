package sisterhood.domain

import kotlinx.datetime.Instant

const val NANO_COEFFICIENT = 1_000_000_000

val Instant.epochNanoseconds: Long
    get() = epochSeconds * NANO_COEFFICIENT + nanosecondsOfSecond.toLong()

fun Instant.Companion.fromEpochNanoseconds(epochNanoseconds: Long): Instant =
    fromEpochSeconds(epochNanoseconds / NANO_COEFFICIENT, epochNanoseconds % NANO_COEFFICIENT)
