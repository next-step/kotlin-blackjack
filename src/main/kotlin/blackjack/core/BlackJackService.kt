package blackjack.core

import blackjack.core.card.CardDispenser
import blackjack.core.player.Dealer
import blackjack.core.player.Players
import blackjack.core.turn.DealerTurnCondition
import blackjack.core.turn.PlayerTurnCondition
import blackjack.core.turn.Turn
import blackjack.presentation.ResultView

object BlackJackService {
    fun standBy(
        dealer: Dealer,
        players: Players,
    ): CardDispenser {
        val cardDispenser = CardDispenser()

        cardDispenser.dealDefault(players)
        cardDispenser.dealDefault(dealer)

        return cardDispenser
    }

    fun play(
        dealer: Dealer,
        players: Players,
        cardDispenser: CardDispenser,
    ) {
        ResultView.printStandby(dealer, players)

        players.play { Turn(it, cardDispenser).process(PlayerTurnCondition) }

        Turn(dealer, cardDispenser).process(DealerTurnCondition)

        dealer.calculateProfit(players)
    }
}
