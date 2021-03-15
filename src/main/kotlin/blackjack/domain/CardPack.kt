package blackjack.domain

internal class CardPack {

    private val cards: MutableList<Card> = Card.ALL_CARD.shuffled().toMutableList()

    fun next(): Card {
        return cards.removeAt(0)
    }
}
