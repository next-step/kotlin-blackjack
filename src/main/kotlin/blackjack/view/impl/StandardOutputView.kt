package blackjack.view.impl

import blackjack.model.Player
import blackjack.view.KoreanMessageSource
import blackjack.view.OutputView

class StandardOutputView(
    private val messageSource: KoreanMessageSource = KoreanMessageSource()
) : OutputView {
    override val printInitCards: (List<Player>) -> Unit = { players ->
        val playerNames = players.joinToString(SEPARATE_SYMBOL) { it.name }

        println()
        println("${playerNames}에게 각각 2장의 카드를 나누었습니다.")
        players.forEach(printPlayerCards)
        println()
    }

    override val printPlayerCards: (Player) -> Unit = { player ->
        println(cardDetailsOf(player))
    }

    override val printResult: (List<Player>) -> Unit = { players ->
        println()
        players.forEach {
            println("${cardDetailsOf(it)} - 결과: ${it.getFinalScore()}")
        }
    }

    private val cardDetailsOf: (Player) -> String = { player ->
        val cardsString = player.cards
            .joinToString(SEPARATE_SYMBOL) { messageSource.nameOf(it) }
        "${player.name}카드: $cardsString"
    }

    companion object {
        private const val SEPARATE_SYMBOL = ","
    }
}
