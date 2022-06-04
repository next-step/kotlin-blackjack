package blackjack.domain

import kotlin.math.abs

data class Score(val origin: Int, val alternative: Int) {

    fun couldGetMoreCard(): Boolean {
        return origin < BLACKJACK || alternative < BLACKJACK
    }

    fun max(): Int {
        return if (abs(BLACKJACK - origin) > abs(BLACKJACK - alternative)) {
            origin
        } else {
            alternative
        }
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
