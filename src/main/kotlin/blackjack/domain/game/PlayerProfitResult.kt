package blackjack.domain.game

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player
import blackjack.domain.score.Score
import blackjack.domain.state.BlackJack
import blackjack.domain.state.Bust
import blackjack.domain.state.Finished
import blackjack.domain.state.GamerState
import blackjack.domain.state.Stay

data class PlayerProfitResult(
    val playerName: String,
    val profit: Double,
) {

    companion object {

        private const val LOSE_MULTIPLE = -1.0
        private const val WIN_MULTIPLE = 1.0
        private const val BLACK_JACK_WIN_MULTIPLE = 1.5
        private const val TIE_MULTIPLE = 0.0

        fun create(
            player: Player,
            dealer: Dealer
        ): PlayerProfitResult {
            val playerState = (player.state as? Finished) ?: throw makePlayerNotFinishedException(player.state)
            val dealerState = (dealer.state as? Finished) ?: throw makePlayerNotFinishedException(dealer.state)

            val profitMultiple = when (playerState) {
                is Bust -> LOSE_MULTIPLE
                is Stay -> calculateProfitByPlayerStay(playerState, dealerState)
                is BlackJack -> calculateProfitByPlayerBlackJack(dealerState)
            }

            return PlayerProfitResult(
                playerName = player.name,
                profit = player.betAmount * profitMultiple
            )
        }

        private fun makePlayerNotFinishedException(state: GamerState): IllegalStateException {
            return IllegalStateException("is not finished state. current : ${state::class.java.simpleName}")
        }

        private fun calculateProfitByPlayerStay(
            playerState: Stay,
            dealerState: Finished,
        ): Double {
            return when (dealerState) {
                is Bust -> WIN_MULTIPLE
                is Stay -> calculateProfitByScore(playerState.cards.score, dealerState.cards.score)
                is BlackJack -> LOSE_MULTIPLE
            }
        }

        private fun calculateProfitByScore(
            playerScore: Score,
            dealerScore: Score,
        ): Double {
            return when {
                dealerScore < playerScore -> WIN_MULTIPLE
                dealerScore == playerScore -> TIE_MULTIPLE
                else -> LOSE_MULTIPLE
            }
        }

        private fun calculateProfitByPlayerBlackJack(
            dealerState: Finished,
        ): Double {
            return when (dealerState) {
                is Bust,
                is Stay -> BLACK_JACK_WIN_MULTIPLE
                is BlackJack -> TIE_MULTIPLE
            }
        }
    }
}

fun List<PlayerProfitResult>.totalProfit(): Double {
    return sumOf { it.profit }
}
