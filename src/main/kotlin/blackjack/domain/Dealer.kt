package blackjack.domain

class Dealer(private val deck: Deck) {
    fun deal(players: Players) {
        repeat(NUMBER_OF_INITIAL_DEAL_CARD) {
            dealOneCardToEachPlayer(players)
        }
    }

    private fun dealOneCardToEachPlayer(players: Players) {
        players.allPlayers().forEach {
            it.receiveCard(deck.draw())
        }
    }

    fun hit(player: Player) {
        player.receiveCard(deck.draw())
    }

    companion object {
        private const val NUMBER_OF_INITIAL_DEAL_CARD = 2
    }
}
