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

        val totalScore = cards.sumOf { it.denomination.score }
        if (hasAce && totalScore <= 11) {
            return totalScore + 10
        }
        return totalScore
    }
}
