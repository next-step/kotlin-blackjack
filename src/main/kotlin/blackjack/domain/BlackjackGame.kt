package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.state.GameResult

class BlackjackGame {
    private val deck = Deck()
    val dealer = Dealer()
    private var players = listOf<Player>()

    fun drawCard() = deck.draw()

    fun start(players: List<Player>) {
        this.players = players
        dealInitialCards()
    }

    fun calculateResult(): Map<Player, GameResult> {
        if (dealer.isBust()) {
            return players.associateWith { GameResult.WIN }
        }

        return players.associateWith { player ->
            determineResult(player, dealer)
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


    private fun determineResult(player: Player, dealer: Dealer): GameResult {
        val playerScore = player.score()
        val dealerScore = dealer.score()

        if (player.isBust()) return GameResult.LOSE
        if (dealer.isBust()) return GameResult.WIN

        return when {
            playerScore > dealerScore -> GameResult.WIN
            playerScore < dealerScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
