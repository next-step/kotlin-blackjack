package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.model.card.CardDeck
import blackjack.model.player.Dealer
import blackjack.model.player.Player
import blackjack.model.strategy.RandomStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        val cards = CardDeck(RandomStrategy().shuffle())
        val players = InputView.inputPlayers().map(::Player)

        val game = BlackjackGame(dealer = Dealer("딜러"), players = players, cards = cards)
        game.initDraw(OutputView::printPlayerInitStatus)
        game.play(InputView::inputPlayerChoice, OutputView::printPlayerCards)
        game.result(OutputView::printResult)
    }
}

fun main() {
    BlackjackController().start()
}
