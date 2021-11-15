package blackjack.domain

class Dealer(private val deck: DealerCardDeck) {
    fun deliverBasicCards(players: Players) {
        players.players.forEach { player ->
            deliverBasicCards(player)
        }
    }

    fun deliverBasicCards(player: Player) {
        repeat(BASIC_CARD_NUMBER) {
            deliverCard(player)
        }
    }

    fun deliverCard(player: Player) {
        player.cardsHandler.addCard(deck.getNextCard())
    }

    companion object {
        const val BASIC_CARD_NUMBER = 2
    }
}
