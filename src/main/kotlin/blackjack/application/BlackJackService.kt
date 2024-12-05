package blackjack.application

import blackjack.core.CardDispenser
import blackjack.core.Name
import blackjack.core.Players
import blackjack.core.Turn
import blackjack.presentation.InputView
import blackjack.presentation.ResultView

object BlackJackService {
    fun start(names: Set<Name>): Players {
        val cardDispenser = CardDispenser()

        val players = Players(names)
        cardDispenser.deal(players, DEFAULT_CARD_NUM)
        ResultView.printStandby(players)

        players.forEach {
            Turn(it, cardDispenser) { InputView.getCard(it) && cardDispenser.checkRemainCard() }
                .process()
        }

        return players
    }

    private const val DEFAULT_CARD_NUM = 2
}
