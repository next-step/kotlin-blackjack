package blackjack

import blackjack.model.CardDeck
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.impl.ConsoleInputView
import blackjack.view.impl.StandardOutputView

class BlackjackApplication(
    private val cardDeck: CardDeck = CardDeck.defaultDeck(),
    private val inputView: InputView = ConsoleInputView(),
    private val outputView: OutputView = StandardOutputView()
) {
    fun play() {
        val playerNames = inputView.readPlayers()
        val players = initPlayers(playerNames)
        outputView.printInitCards(players)

        playBlackjackGame(
            players, cardDeck,
            inputView.readPlayerAnswer,
            outputView.printPlayerCards
        )

        outputView.printResult(players)
    }

    private fun initPlayers(names: String): List<Player> {
        return splitPlayerNames(names).map { name ->
            Player(name, cardDeck.drawCards(INIT_CARD_COUNT))
        }
    }

    private fun splitPlayerNames(names: String): List<String> {
        return names.split(NAME_STRING_DELIMITER).map { it.trim() }
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

            player.addCard(cardDeck.drawCard())
            cardsPrinter(player)
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
        private const val CARD_PICK_STOP_SYMBOL = "n"
        private const val NAME_STRING_DELIMITER = ","
    }
}

fun main() {
    BlackjackApplication().play()
}
