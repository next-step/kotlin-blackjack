package blackjack.domain

abstract class Participant(val name: String) {
    val cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    abstract fun canDrawMoreCard(): Boolean
}
