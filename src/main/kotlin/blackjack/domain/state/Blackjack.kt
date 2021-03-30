package blackjack.domain.state

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.domain.winning.GameResult

class Blackjack: Finished() {
    override fun isWinning(player: Player, dealer: Dealer): GameResult {
        if(dealer.state is Blackjack) return GameResult.DRAW
        return GameResult.WIN
    }

    override fun profit(money: Double): Double  {
        return money * 1.5
    }
}