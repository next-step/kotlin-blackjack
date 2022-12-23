package blackjack

import blackjack.model.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

private const val INIT_CARD_COUNT = 2
private const val CARD_PICK_DENY_SYMBOL = "n"

class BlackjackApplication {
    fun play() {
        val deck = CardDeck.defaultDeck()
        val players = InputView.readPlayers()
        players.forEach { player -> repeat(INIT_CARD_COUNT) { player.addCard(deck.getCard()) } }

        OutputView.printInitCards(players)

        players.forEach {
            while (it.isPickable()) {
                val answer = InputView.readPlayerPickAnswer(it)
                if (answer == CARD_PICK_DENY_SYMBOL) {
                    break
                }
                it.addCard(deck.getCard())
                OutputView.printPlayerCards(it)
            }
        }
        OutputView.printResult(players)
    }
}

fun main() {
    BlackjackApplication().play()
}
