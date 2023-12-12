package game.blackjack.domain

class HandCards {
    private val handCards: MutableList<Card> = mutableListOf()
    val size: Int get() = handCards.size

    fun add(card: Card) {
        handCards.add(card)
    }

    fun add(cards: List<Card>) {
        handCards.addAll(cards)
    }

    fun getCurrentScore(): Int {
        val score = handCards.sumOf { it.number.number }

        return adjustAceScore(score)
    }

    private fun adjustAceScore(originScore: Int): Int {
        var score = originScore

        handCards.filter { it.number == CardNumber.ACE }
            .filter { score + 10 <= Blackjack.WINNING_SCORE }
            .forEach { _ -> score += 10 }

        return score
    }

    override fun toString(): String {
        return handCards.map { it.toString() }.joinToString(", ")
    }
}
