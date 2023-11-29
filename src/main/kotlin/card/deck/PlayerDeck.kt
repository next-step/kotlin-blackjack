package card.deck

import card.PlayingCard

class PlayerDeck(override val cardDeck: MutableList<PlayingCard>) : CardDeck {

    fun addCard(playingCard: PlayingCard) {
        cardDeck.add(playingCard)
    }

    companion object {
        fun create() = PlayerDeck(mutableListOf())
    }
}
