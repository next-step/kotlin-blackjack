package blackjack.controller

import blackjack.domain.BlackJackManager
import blackjack.domain.CardDeck
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

object GameController {

    const val BLACK_JACK_SCORE = 21
    private val cardDeck = CardDeck()
    private lateinit var blackJackManager: BlackJackManager

    fun start() {
        val playerNames = InputView.inputPlayerNames()
        val players = Players.of(*Players.getPlayerListByNames(playerNames).toTypedArray())

        blackJackManager = BlackJackManager(players)
        blackJackManager.giveInitialCards(cardDeck)

        OutputView.printPlayers(players)
        OutputView.printPlayersDrawnCards(players)

        blackJackManager.hitPlayer(question = InputView::acceptMoreCard, cardDeck = cardDeck, printResult = OutputView::printPlayerDrawnCard)

        OutputView.printResult(players)
    }
}
