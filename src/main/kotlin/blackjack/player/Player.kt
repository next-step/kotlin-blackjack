package blackjack.player

import blackjack.card.Card

class Player(val name: String, private val playerCards: PlayerCards = PlayerCards()) {
    fun getCard(card: Card) {
        playerCards.add(card)
    }

    fun show(): List<Card> {
        return playerCards.show()
    }
}
