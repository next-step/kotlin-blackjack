package blackjack.domain.result.game

import blackjack.domain.Dealer
import blackjack.domain.batting.BetBoard
import blackjack.domain.player.DealerPlayer

data class DealerGameResult(
    val dealer: DealerPlayer,
    val profit: Profit,
) {

    companion object {
        fun of(dealer: Dealer, betBoard: BetBoard): DealerGameResult =
            DealerGameResult(
                dealer.player,
                betBoard.dealerProfit()
            )
    }
}
