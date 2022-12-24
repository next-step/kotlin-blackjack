package blackjack

import blackjack.model.CardDeck
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

private const val INIT_CARD_COUNT = 2
private const val CARD_PICK_STOP_SYMBOL = "n"

class BlackjackApplication {
    fun play() {
        val players = InputView.readPlayers()
        val cardDeck = CardDeck.defaultDeck()
        initPlayers(players, cardDeck)

        OutputView.printInitCards(players)

        playBlackjackGame(
            players, cardDeck,
            InputView.readPlayerPickAnswer,
            OutputView.printPlayerCards
        )

        OutputView.printResult(players)
    }

    private fun initPlayers(players: List<Player>, cardDeck: CardDeck) {
        repeat(INIT_CARD_COUNT) {
            players.forEach { it.addCard(cardDeck.getCard()) }
        }
    }

    private fun playBlackjackGame(
        players: List<Player>,
        cardDeck: CardDeck,
        answerReader: (Player) -> String,
        cardsPrinter: (Player) -> Unit
    ) {
        players.forEach { player ->
            pickCardsUntilStopAnswered(player, cardDeck, answerReader, cardsPrinter)
        }
    }

    private fun pickCardsUntilStopAnswered(
        player: Player,
        cardDeck: CardDeck,
        answerReader: (Player) -> String,
        cardsPrinter: (Player) -> Unit
    ) {
        while (player.isPickable()) {
            val answer = answerReader(player)
            if (answer == CARD_PICK_STOP_SYMBOL) {
                break
            }

            player.addCard(cardDeck.getCard())
            cardsPrinter(player)
        }
    }
}

fun main() {
    BlackjackApplication().play()
}
