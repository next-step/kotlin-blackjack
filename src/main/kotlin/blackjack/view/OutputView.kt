package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object OutputView {
    private const val PLAYER_NAME_DELIMITER = ", "
    private const val BLANK_PREFIX_MESSAGE = ""
    private const val POST_MESSAGE = "에게 2장의 나누었습니다."
    private const val RESULT_EXPRESSION = " - 결과: "
    private const val NAME_POSTFIX_EXPRESSION = "카드: "

    fun printFirstAllPlayersCards(players: Players) {
        println()
        val result =
            players.getPlayerNames().joinToString(
                PLAYER_NAME_DELIMITER,
                BLANK_PREFIX_MESSAGE,
                POST_MESSAGE,
            )
        println(result)
        players.forEach { player ->
            printPlayerAllCards(player)
            println()
        }
    }

    fun printFinalResults(players: Players) {
        println()
        println()
        println("게임 종료!")
        players.forEach { player ->
            printPlayerAllCards(player)
            resultExpression(player)
        }
    }

    fun printPlayerCard(player: Player) {
        printPlayerAllCards(player)
    }

    private fun printPlayerAllCards(player: Player) {
        print(player.name + NAME_POSTFIX_EXPRESSION)
        print(player.findAllCardsNames().joinToString(PLAYER_NAME_DELIMITER))
    }

    private fun resultExpression(player: Player) {
        print(RESULT_EXPRESSION)
        println(player.calculateCardPoints())
    }
}
