package blackjack

import blackjack.model.CardDeck
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackApplication {
    fun play() {
        val playerNames = InputView.readPlayers()
        val cardDeck = CardDeck.defaultDeck()

        val players = initPlayers(playerNames, cardDeck)
        OutputView.printInitCards(players)

        playBlackjackGame(
            players, cardDeck,
            InputView.readPlayerPickAnswer,
            OutputView.printPlayerCards
        )

        OutputView.printResult(players)
    }

    fun initPlayers(names: String, deck: CardDeck): List<Player> {
        return splitPlayerNames(names).map { name ->
            Player(name, deck.drawCards(INIT_CARD_COUNT))
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
