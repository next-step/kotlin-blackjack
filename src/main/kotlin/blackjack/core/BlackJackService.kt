package blackjack.core

import blackjack.core.card.CardDispenser
import blackjack.core.player.Dealer
import blackjack.core.player.Players
import blackjack.core.turn.DealerTurnCondition
import blackjack.core.turn.PlayerTurnCondition
import blackjack.core.turn.Turn
import blackjack.presentation.ResultView

object BlackJackService {
    fun start(
        dealer: Dealer,
        players: Players,
    ) {
        val cardDispenser = CardDispenser()

        cardDispenser.dealDefault(players)
        cardDispenser.dealDefault(dealer)

        ResultView.printStandby(dealer, players)

        players.play { Turn(it, cardDispenser).process(PlayerTurnCondition) }

        Turn(dealer, cardDispenser).process(DealerTurnCondition)

        dealer.calculateProfit(players)
    }
}
