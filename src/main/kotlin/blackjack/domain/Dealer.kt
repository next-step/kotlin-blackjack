package blackjack.domain

class Dealer(private val deck: DealerCardDeck) {
    fun deliverBasicCards(players: Players) {
        players.players
            .forEach { player -> repeat(BASIC_CARD_NUMBER) { player.cardsHandler.addCard(deck.getNextCard()) } }
    }

    fun deliverAdditionalCard(player: Player) {
        player.cardsHandler.addCard(deck.getNextCard())
    }

    companion object {
        const val BASIC_CARD_NUMBER = 2
    }
}
