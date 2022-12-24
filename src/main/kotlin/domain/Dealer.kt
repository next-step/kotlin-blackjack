package domain

import domain.factory.DefaultCardsFactory

class Dealer(
    val deck: CardDeck = CardDeck(cardsFactory = DefaultCardsFactory)
) {
    init {
        deck.shuffle()
    }

    fun giveCard(player: Player) {
        val card = deck.getTopCard()
        player.receiveCard(card = card)
    }
}
