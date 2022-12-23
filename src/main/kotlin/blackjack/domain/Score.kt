package blackjack.domain

import kotlin.math.abs

data class Score(
    val value: Int
) {
    init {
        require(value >= 0) { "점수값은 음수가 될 수 없습니다." }
    }

    fun differenceValue(other: Score): Int {
        return abs(other.value - value)
    }

    override fun toString(): String {
        return "$value"
    }
}

fun Int.toScore(): Score {
    return Score(this)
}
