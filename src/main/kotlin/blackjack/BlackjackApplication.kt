package blackjack

import blackjack.domain.CardRandomShuffler
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackApplication {

    companion object {
        private const val INIT_DRAW_CARD_COUNT = 2

        @JvmStatic
        fun main(args: Array<String>) {
            val players = InputView.readPlayers()
            OutputView.drawCardMessage(players, INIT_DRAW_CARD_COUNT)
            val deck = Deck(CardRandomShuffler())
            val dealer = Dealer(deck)
            dealer.handCards(count = 2, players = players)
            OutputView.playerCardMessage(players)
        }
    }

}
