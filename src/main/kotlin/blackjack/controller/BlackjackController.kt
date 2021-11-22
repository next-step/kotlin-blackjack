package blackjack.controller

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Gamer
import blackjack.model.Gamers
import blackjack.model.Name
import blackjack.model.Player
import blackjack.view.DrawAction
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {

    private val inputView = InputView()
    private val outputView = OutputView()

    private var deck: Deck = Deck.empty()

    fun play() {
        deck = Deck.shuffled()

        createGamers()
            .ready()
            .also { outputView.printReady(it) }
            .start()
            .also { outputView.printResult(it) }
    }

    private fun createGamers(): Gamers {
        val players = inputView.getPlayers()
        val dealer = Dealer.ready(Name.valueOf("딜러"))
        return Gamers.from(dealer, players)
    }

    private fun Gamers.ready(): Gamers = drawUntilStarted { peekAndDraw() }

    private fun Gamers.start(): Gamers = drawWhile(
        next = { if (askDraw(it)) peekAndDraw() else null },
        onDraw = { outputView.printRunning(it) }
    )

    private fun peekAndDraw(): Card? {
        val card = deck.peek() ?: return null
        deck = deck.draw()
        return card
    }

    private fun askDraw(gamer: Gamer): Boolean = when (gamer) {
        is Dealer -> gamer.shouldDraw()
        is Player -> inputView.askDraw(gamer) == DrawAction.YES
    }
}
