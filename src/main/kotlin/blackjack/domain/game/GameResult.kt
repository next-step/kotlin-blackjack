package blackjack.domain.game

import blackjack.domain.card.Denomination
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer

enum class GameResult(
    val message: String
) {
    WIN("승"),
    LOSE("패");

    fun reverse(): GameResult {
        if (this == WIN) {
            return LOSE
        }
        return WIN
    }

    companion object {
        fun calculate(dealer: Dealer, gamer: Gamer): GameResult {
            if (hasDealerHigherScoreThanWinningNumber(dealer) || hasGamerHigherScoreThanDealer(dealer, gamer)) {
                return WIN
            }
            return LOSE
        }

        private fun hasDealerHigherScoreThanWinningNumber(dealer: Dealer) =
            dealer.getDeckScore() > Denomination.WINNING_NUMBER

        private fun hasGamerHigherScoreThanDealer(dealer: Dealer, gamer: Gamer): Boolean {
            return gamer.getDeckScore() <= Denomination.WINNING_NUMBER &&
                    dealer.getDeckScore() < gamer.getDeckScore()
        }
    }
}
