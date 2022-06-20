package blackjack.domain.score

import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState

object ScoreCalculator {
    fun calculate(dealer: PlayerResult, player: PlayerResult): ScoreType = when (dealer.finalState) {
        PlayerState.Blackjack -> when (player.finalState) {
            PlayerState.Blackjack -> ScoreType.DRAW
            else -> ScoreType.LOSE
        }
        PlayerState.Busted -> when (player.finalState) {
            PlayerState.Busted -> ScoreType.LOSE
            else -> ScoreType.WIN
        }
        else -> when (player.finalState) {
            PlayerState.Busted -> ScoreType.LOSE
            else -> compareTotal(player.player.cards.total.value, dealer.player.cards.total.value)
        }
    }

    private fun compareTotal(playerTotal: Int, dealerTotal: Int): ScoreType {
        val difference = playerTotal - dealerTotal

        return when {
            difference > 0 -> ScoreType.WIN
            difference == 0 -> ScoreType.DRAW
            else -> ScoreType.LOSE
        }
    }
}
