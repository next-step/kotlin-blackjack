package blackjack.view

import blackjack.model.Player

object OutputView {
    private const val SEPARATE_SYMBOL = ","
    private val messageSource = KoreanMessageSource()

    val printInitCards: (List<Player>) -> Unit = { players ->
        println()
        val playerNames = players.joinToString(SEPARATE_SYMBOL) { it.name }
        println("${playerNames}에게 각각 2장의 카드를 나누었습니다.")
        players.forEach(printPlayerCards)
        println()
    }

    val printPlayerCards: (Player) -> Unit = { player ->
        println(cardDetailsOf(player))
    }

    val printResult: (List<Player>) -> Unit = { players ->
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
}
