package blackjack.view.impl

import blackjack.model.DualResult
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.KoreanMessageSource
import blackjack.view.OutputView

class StandardOutputView(
    private val messageSource: KoreanMessageSource = KoreanMessageSource()
) : OutputView {
    override val printInitCards: (Player, Players) -> Unit = { dealer, players ->
        val playerNames = players.joinToString(SEPARATE_SYMBOL) { it.name }

        println()
        println("${dealer.name}와 ${playerNames}에게 각각 2장의 카드를 나누었습니다.")

        printPlayerCards(dealer)
        players.forEach(printPlayerCards)
        println()
    }

    override val printPlayerCards: (Player) -> Unit = { player ->
        println(cardDetailsOf(player))
    }

    override val printDealerDraw: (Player) -> Unit = { player ->
        println()
        println("${player.name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    override val printCardResult: (Player, Players) -> Unit = { dealer, players ->
        val message: (Player) -> String = { "${cardDetailsOf(it)} - 결과: ${it.getFinalScore()}" }

        println()
        println(message(dealer))
        players.forEach {
            println(message(it))
        }
    }

    private val cardDetailsOf: (Player) -> String = { player ->
        val cardsString = player.cards
            .joinToString(SEPARATE_SYMBOL) { messageSource.nameOf(it) }
        "${player.name}카드: $cardsString"
    }

    override val printGameResult: (Players, GameResult) -> Unit = { players, gameResult ->
        println()
        println("딜러: ${getDealerResultMessage(gameResult)}")
        println(getPlayersResultMessage(players, gameResult))
    }

    private fun getDealerResultMessage(gameResult: GameResult): String {
        return DualResult.values()
            .map { it to gameResult.getDealerResultCountOf(it) }
            .filterNot { it.second == null }
            .joinToString(" ") { "${it.second}${messageSource.nameOf(it.first)}" }
    }

    private fun getPlayersResultMessage(players: Players, gameResult: GameResult): String {
        return players.joinToString("\n") {
            "${it.name}: ${messageSource.nameOf(gameResult.getPlayerDualResultOf(it))}"
        }
    }

    companion object {
        private const val SEPARATE_SYMBOL = ","
    }
}
