package baclkjack.domain.play

import baclkjack.domain.card.Card
import baclkjack.domain.card.Suit

class Cards {
    private val cards = mutableListOf<Card>()
    fun add(card: Card) {
        cards.add(card)
    }

    fun score(): Int {

        var sum = cards.map { it.number }.sumOf { it.value }
        val aceCardCount = cards.count { it.number.isAce() }
        repeat(aceCardCount) {
            if (sum > WIN_NUMBER) {
                sum -= ACE_VALUE
            }
        }

        return sum
    }

    fun isBurst(): Boolean = score() > WIN_NUMBER

    override fun toString(): String = cards.map { "${it.number.value}${it.suit.toName()}" }.joinToString { it }

    companion object {
        const val ACE_VALUE = 10
        const val WIN_NUMBER = 21
    }
}

fun Suit.toName() = when (this) {
    Suit.SPADE -> "스페이스"
    Suit.HEART -> "하트"
    Suit.DIAMOND -> "다이아몬드"
    Suit.CLUB -> "클로버"
}
