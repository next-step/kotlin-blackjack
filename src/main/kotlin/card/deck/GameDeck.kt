package card.deck

import card.PlayingCard

class GameDeck(override val cardDeck: MutableList<PlayingCard>) : CardDeck {

    init {
        cardDeck.shuffle()
    }
    private var index = 0

    fun getCardWithIncrease(): PlayingCard {
        return cardDeck[index++]
    }

    fun addCards(cards: List<PlayingCard>) {
        this.cardDeck.addAll(cards)
    }
}
