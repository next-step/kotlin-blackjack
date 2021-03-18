package blackjack.domain

internal class CardPack {

    private var cards: MutableList<Card> = mutableListOf()

    fun next(): Card {
        if (this.cards.isEmpty()) {
            this.cards = Card.ALL_CARD.shuffled().toMutableList()
        }

        return cards.removeAt(0)
    }
}
