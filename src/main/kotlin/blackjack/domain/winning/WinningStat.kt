package blackjack.domain.winning

import blackjack.domain.player.Player
import blackjack.domain.score.PlayerScore

class WinningStat(
    playerScores: List<PlayerScore>,
    dealerScore: PlayerScore,
) {
    private val scores: List<PlayerScore> = playerScores
    private val dealerScore: Int = dealerScore.score
    private val dealer: Player = dealerScore.player
    val result = playerGameResult()

    fun playerGameResult(): List<PlayerGameResult> {
        return scores.map {
            PlayerGameResult(
                it.player,
                playerWinningState(it)
            )
        }
    }

    private fun playerWinningState(playerScore: PlayerScore): WinningState {
        return when {
            dealerBust() -> WinningState.DEALER_BUST
            playerBust(playerScore) -> WinningState.PLAYER_BUST
            playerBlackJack(playerScore) -> WinningState.PLAYER_BLACKJACK
            playerBlackJack(playerScore) && dealerBlackJack() -> WinningState.PLAYER_TIE
            else -> when (compareTo(playerScore.score)) {
                0 -> WinningState.PLAYER_TIE
                1 -> WinningState.PLAYER_WIN
                else -> WinningState.PLAYER_LOOSE
            }
        }
    }

    private fun dealerBust(): Boolean = dealerScore > BLACK_JACK_SCORE

    private fun dealerBlackJack(): Boolean = (dealerScore == BLACK_JACK_SCORE && dealer.cards.size == 2)

    private fun playerBust(playerScore: PlayerScore): Boolean = playerScore.score > BLACK_JACK_SCORE

    private fun playerBlackJack(playerScore: PlayerScore): Boolean = (playerScore.score == BLACK_JACK_SCORE && playerScore.player.cards.size == 2)

    private fun compareTo(score: Int): Int = score.compareTo(dealerScore)

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
