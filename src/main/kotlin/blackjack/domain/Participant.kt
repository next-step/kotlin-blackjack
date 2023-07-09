package blackjack.domain

abstract class Participant(val name: String) {
    val cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return cards.calculateScore() > Cards.BLACKJACK
    }

    abstract fun canDrawMoreCard(): Boolean
}
