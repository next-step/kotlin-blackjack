package blackjack

import blackjack.model.CardDeck
import blackjack.model.Player
import blackjack.model.Players
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

        playBlackjackGame(players)
        outputView.printResult(players)
    }

    private fun initPlayers(names: String): Players {
        return Players(splitNames(names).map { initPlayer(it) })
    }

    private fun initPlayer(name: String): Player {
        return Player(name, cardDeck.drawCards(INIT_CARD_COUNT))
    }

    private fun splitNames(names: String): List<String> {
        return names.split(NAME_STRING_DELIMITER).map { it.trim() }
    }

    private fun playBlackjackGame(players: Players) {
        players.forEach { player ->
            pickCardsUntilStopAnswered(player)
        }
    }

    private fun pickCardsUntilStopAnswered(player: Player) {
        while (player.isPickable()) {
            if (!inputView.readPickAnswer(player)) {
                break
            }

            player.addCard(cardDeck.drawCard())
            outputView.printPlayerCards(player)
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
        private const val NAME_STRING_DELIMITER = ","
    }
}

fun main() {
    BlackjackApplication().play()
}
