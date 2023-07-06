package blackjack.domain

class Player(val name: String) {
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun canDrawMoreCard(): Boolean {
        return calculateScore() < 21
    }

    fun calculateScore(): Int {
        val hasAce = cards.any { it.denomination == Denomination.ACE }

        val totalScore = cards.sumOf { it.denomination.score }.let {
            if (hasAce && it <= 11) {
                return it + 10
            }
            it
        }
        return totalScore
    }
}
