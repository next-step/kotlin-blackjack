package blackjack.domain.state

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.domain.winning.GameResult

class Bust : Finished() {
    override fun isWinning(player: Player, dealer: Dealer): GameResult {
        return GameResult.LOSE
    }

    override fun profit(money: Double): Double {
        return money * 0
    }
}
