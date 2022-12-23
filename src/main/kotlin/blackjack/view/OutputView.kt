package blackjack.view

import blackjack.model.Player

object OutputView {
    private const val SEPARATE_SYMBOL = ","
    private val messageSource = KoreanMessageSource()

    fun printInitCards(players: List<Player>) {
        println()
        val playerNames = players.joinToString(SEPARATE_SYMBOL) { it.name }
        println("${playerNames}에게 각각 2장의 카드를 나누었습니다.")
        players.forEach(::printPlayerCards)
        println()
    }

    fun printPlayerCards(player: Player) {
        println(cardDetailsOf(player))
    }

    fun printResult(players: List<Player>) {
        println()
        players.forEach {
            println("${cardDetailsOf(it)} - 결과: ${it.getFinalScore()}")
        }
    }

    private fun cardDetailsOf(player: Player): String {
        val cardsString = player.cards
            .joinToString(SEPARATE_SYMBOL) { messageSource.nameOf(it) }
        return "${player.name}카드: $cardsString"
    }
}
