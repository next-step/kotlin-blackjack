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
        return cards.sumOf { it.denomination.score }
    }
}
