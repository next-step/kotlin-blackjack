package blackjack.controller

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.view.DrawAction
import blackjack.model.Gamer
import blackjack.model.Gamers
import blackjack.model.Name
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {

    private val inputView = InputView()
    private val outputView = OutputView()

    private var deck: Deck = Deck.empty()

    fun play() {
        deck = Deck.shuffled()

        createGamers()
            .start()
            .also { outputView.printStart(it) }
            .run()
            .also { outputView.printResult(it) }
    }

    private fun createGamers(): Gamers {
        val names = inputView.getNames() ?: return Gamers.empty()
        val players = names.toList().map { Player.ready(it) }
        val dealer = Dealer.ready(Name.valueOf("딜러"))
        return Gamers.from(dealer, players)
    }

    private fun Gamers.start(): Gamers = drawUntilStarted { peekAndDraw() }

    private fun Gamers.run(): Gamers = drawWhile(
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
