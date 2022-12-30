package blackjack.view.impl

import blackjack.model.MatchResult
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

    override val printGameResult: (
        Map<MatchResult, Int>, Map<Player, MatchResult>
    ) -> Unit = { dealerResult, playersResult ->
        println()
        println("딜러: ${getDealerResultMessage(dealerResult)}")
        println(getPlayersResultMessage(playersResult))
    }

    private fun getPlayersResultMessage(playersResult: Map<Player, MatchResult>): String {
        return playersResult.entries
            .joinToString("\n") {
                val player = it.key
                val playerResult = playersResult[player]
                "${player.name}: ${messageSource.nameOf(playerResult!!)}"
            }
    }

    private fun getDealerResultMessage(dealerResult: Map<MatchResult, Int>): String {
        return dealerResult.entries
            .filterNot { it.value == 0 }
            .joinToString(" ") { "${it.value}${messageSource.nameOf(it.key)}" }
    }

    companion object {
        private const val SEPARATE_SYMBOL = ","
    }
}
