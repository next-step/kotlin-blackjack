package blackjack.domain.state

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.domain.winning.GameResult

class Blackjack : Finished() {
    override fun profit(money: Double): Double {
        return money * 1.5
    }
}
