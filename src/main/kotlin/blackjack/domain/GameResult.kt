package blackjack.domain

import kotlin.math.abs

class GameResult(
    private val dealer: Dealer,
    private val players: List<Participant>
) {
    val allParticipant = listOf(dealer) + players
    fun setDealerIsWin() {
        players.forEach {
            this.dealer.gameScore.win()
            it.gameScore.lose()
        }
    }

    fun decideWinner() {
        players.forEach {
            decideWinner(dealer, it)
        }
    }

    private fun decideWinner(dealer: Dealer, player: Participant) {
        if (abs(BLACK_JACK_SCORE - player.playerCards.score()) < BLACK_JACK_SCORE - dealer.playerCards.score()) {
            player.gameScore.win()
            dealer.gameScore.lose()
        } else if (player.playerCards.score() == dealer.playerCards.score()) {
            player.gameScore.draw()
            dealer.gameScore.draw()
        } else {
            player.gameScore.lose()
            dealer.gameScore.win()
        }
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
