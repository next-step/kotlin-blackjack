package blackjack.dealer

import blackjack.player.Hand
import blackjack.player.Player
import blackjack.player.ScoreCalculator

class Dealer(
    private val hand: Hand = Hand(),
) {

    fun dealInitialCards(players: List<Player>) {
        repeat(INITIAL_CARD_COUNT) { hand.addCard(CardDeck.draw()) }
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

    fun drawCardFromDeck() {
        hand.addCard(CardDeck.draw())
    }

    val displayHand: String
        get() = hand.displayCards

    val totalValue: Int
        get() = ScoreCalculator.calculateScore(hand)

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
