package blackjack.domain

class TrumpCardBuilder {

    private val cards = mutableListOf<Card>()

    fun cards(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun build(): TrumpCard {
        return TrumpCard(cards)
    }
}
