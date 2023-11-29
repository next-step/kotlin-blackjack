package card.deck

import card.PlayingCard

class GameDeck private constructor(override val cardDeck: MutableList<PlayingCard>) : CardDeck {

    private var index = 0

    fun getCardWithIncrease(): PlayingCard {
        return cardDeck[index++]
    }

    fun addCards(cards: List<PlayingCard>) {
        this.cardDeck.addAll(cards)
    }

    companion object {
        fun create(cards: List<PlayingCard>) = GameDeck(cards.toMutableList())
    }
}
