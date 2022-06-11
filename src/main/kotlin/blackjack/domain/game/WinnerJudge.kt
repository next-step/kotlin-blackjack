package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class WinnerJudge(private val players: List<Player>, private val dealer: Dealer) {

    init {
        calculateWinner()
    }

    private fun calculateWinner() {
        players.forEach {
            judge(it, dealer)
        }
    }

    private fun judge(player: Player, dealer: Dealer) {
        if (checkPlayerWin(player, dealer)) {
            player.isWinner = true
            dealer.lose++
        }

        if (checkDealerWin(player, dealer)) {
            player.isWinner = false
            dealer.win++
        }
    }

    private fun checkPlayerWin(player: Player, dealer: Dealer): Boolean {
        if (player.score > BLACKJACK_SCORE) {
            return false
        }

        return dealer.score > BLACKJACK_SCORE || player.score > dealer.score || player.score == BLACKJACK_SCORE
    }

    private fun checkDealerWin(player: Player, dealer: Dealer): Boolean {
        if (dealer.score > BLACKJACK_SCORE) {
            return false
        }

        return player.score > BLACKJACK_SCORE || player.score < dealer.score || dealer.score == BLACKJACK_SCORE
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
