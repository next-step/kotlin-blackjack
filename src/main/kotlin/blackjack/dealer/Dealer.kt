package blackjack.dealer

import blackjack.player.Player

class Dealer {

    fun dealInitialCards(players: List<Player>) {
        players.forEach {
            dealInitialCard(it)
        }
    }
    private fun dealInitialCard(player: Player) {
        repeat(INITIAL_CARD_COUNT) { drawCard(player) }
    }
    fun drawCard(player: Player) {
        player.addCard(CardDeck.draw())
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
