package blackjack.domain

abstract class Participant(val name: String) {
    val cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun canDrawMoreCard(): Boolean {
        if (this is Dealer) return false
        return cards.canDrawMoreCard()
    }
}
