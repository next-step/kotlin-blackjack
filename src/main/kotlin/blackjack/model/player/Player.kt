package blackjack.model.player

import blackjack.model.card.Card

data class Player(val name: String) {
    var cards: MutableList<Card> = mutableListOf()

    fun receiveCard(card: Card) {
        cards.add(card)
    }
}
