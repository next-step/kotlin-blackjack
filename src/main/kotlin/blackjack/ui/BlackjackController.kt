package blackjack.ui

import blackjack.domain.DrawDecider
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.player.PlayerFactory
import blackjack.domain.player.Players
import view.ConsoleInput
import view.ConsoleOutput
import java.util.Stack

object BlackjackController {
    private val consoleInput = ConsoleInput()
    private val consoleOutput = ConsoleOutput()

    fun run() {
        consoleOutput.printUserNameInputMessage()
        val players = PlayerFactory.create(consoleInput.read())
        val cardDeck = CardDeck.shuffle()

        proceedRound(players, cardDeck)
        consoleOutput.printGameResult(players)
    }

    private fun proceedRound(players: Players, cardDeck: Stack<Card>) {
        players.drawAtFirst(cardDeck)
        consoleOutput.printFirstDrawMessage(players)

        players.players.forEach {
            consoleOutput.printDecideDrawingMessage(it)
            it.draw(cardDeck.pop(), DrawDecider.of(consoleInput.read()))
            consoleOutput.printHandsStatus(it)
        }
    }
}
