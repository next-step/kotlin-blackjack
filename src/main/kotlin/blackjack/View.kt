package blackjack

import blackjack.vo.PlayerVO

object InputView {
    private const val PLAYER_NAME_DELIMITER = ","

    fun readPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readlnOrNull() ?: throw IllegalArgumentException("이름을 입력해주세요.")
        return names.split(PLAYER_NAME_DELIMITER)
    }
}

object ResultView {
    private const val COMMA_SEPARATOR = ", "
    private const val NEW_LINE = "\n"

    fun printCardHands(players: List<PlayerVO>) {
        val namesText = players.joinToString(COMMA_SEPARATOR) { it.name }
        val playersText = players.joinToString(NEW_LINE) { player ->
            val cards = player.cards.joinToString(COMMA_SEPARATOR) { "${it.denomination.symbol}${it.suit.name}" }
            "${player.name}카드: $cards"
        }
        println(
            """
            |${namesText}에게 2장의 카드를 나누었습니다.
            |$playersText
            |""".trimMargin()
        )
    }
}
