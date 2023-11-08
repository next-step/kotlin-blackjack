package blackjack.domain

class TrumpCardBuilder {

    private val cards = mutableListOf<Card>()

    fun card(card: Card) {
        cards.add(card)
    }

    fun build(): TrumpCard {
        return TrumpCard(cards)
    }
}