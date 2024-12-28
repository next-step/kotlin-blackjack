package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class BlackjackGame {
    private val deck = Deck()
    val dealer = Dealer()
    private var players = listOf<Player>()

    fun drawCard() = deck.draw()

    fun start(players: List<Player>) {
        this.players = players
        dealInitialCards()
    }

    fun calculateProfits(
        players: List<Player>,
        dealer: Dealer,
    ): Map<Player, Double> {
        val dealerState = dealer.getState()

        return players.associateWith { player ->
            player.calculateProfit(dealerState)
        }
    }

    fun calculateDealerProfit(
        players: List<Player>,
        dealer: Dealer,
    ): Double {
        val dealerState = dealer.getState()

        return -players.sumOf { player ->
            player.calculateProfit(dealerState)
        }
    }

    private fun dealInitialCards() {
        repeat(INITIAL_CARD_COUNT) {
            dealer.drawCard(deck.draw())
            players.forEach { player ->
                player.drawCard(deck.draw())
            }
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
