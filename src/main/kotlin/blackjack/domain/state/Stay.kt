package blackjack.domain.state

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.domain.winning.GameResult

class Stay : Finished() {
    override fun isWinning(player: Player, dealer: Dealer): GameResult {
        if (player.getScore() < dealer.getScore()) return GameResult.WIN
        if (player.getScore() > dealer.getScore()) return GameResult.LOSE
        return GameResult.DRAW
    }

    override fun profit(money: Double): Double {
        return money * 1
    }
}
