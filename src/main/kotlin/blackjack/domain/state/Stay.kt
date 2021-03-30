package blackjack.domain.state

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.domain.winning.GameResult

class Stay : Finished() {
    override fun isWinning(player: Player, dealer: Dealer): GameResult {
        if (player.getScore() < dealer.getScore()) return GameResult.LOSE
        if (player.getScore() > dealer.getScore()) return GameResult.WIN
        return GameResult.DRAW
    }

    override fun profit(money: Double): Double {
        return money * 1
    }
}
