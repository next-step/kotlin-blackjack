package blackjack.dealer

import blackjack.player.Hand
import blackjack.player.Player

class Dealer(
    hand: Hand = Hand()
) : Player(DEALER_NAME, hand = hand) {
    fun dealInitialCards(players: List<Player>) {
        dealInitialCard(this)
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
        const val DEALER_NAME = "딜러"
        private const val INITIAL_CARD_COUNT = 2
    }
}
