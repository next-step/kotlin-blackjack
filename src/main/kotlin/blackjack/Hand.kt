package blackjack

import kotlin.math.abs

data class Hand(private val cards: List<Card>) : List<Card> by cards {
    fun blackjack(): Boolean = size == 2 && score() == CardPlayer.BLACKJACK

    fun score(): Int {
        return cards.fold(listOf(0)) { accumulator, card ->
            accumulator.flatMap { score -> card.number.map { it + score } }
        }.closeTo(CardPlayer.BLACKJACK)
    }

    private fun List<Int>.closeTo(number: Int): Int {
        val sorted = map { it to abs(it - number) }
            .sortedBy { it.second }
            .map { it.first }
        val result = sorted.firstOrNull { it <= CardPlayer.BLACKJACK }
        return result ?: sorted.firstOrNull() ?: 0
    }
}
