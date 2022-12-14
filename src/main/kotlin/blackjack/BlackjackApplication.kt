package blackjack

import blackjack.model.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackjackApplication {
    private const val INIT_CARD_COUNT = 2

    @JvmStatic
    fun main(args: Array<String>) {
        val deck = CardDeck()
        val players = InputView.readPlayers()
        players.forEach { player -> repeat(INIT_CARD_COUNT) { player.addCard(deck.getSingleCard()) } }

        OutputView.printInitCards(players)

        players.forEach {
            while (it.isPickable()) {
                val answer = InputView.readPlayerPickAnswer(it)
                if (answer == "n") {
                    break
                }
                it.addCard(deck.getSingleCard())
            }
            OutputView.printPlayerCards(it)
        }
        OutputView.printResult(players)
    }
}
