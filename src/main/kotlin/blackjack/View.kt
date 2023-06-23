package blackjack

import blackjack.vo.PlayerScoreVO
import blackjack.vo.PlayerVO

object InputView {
    private const val COMMA_SEPARATOR = ","

    fun readPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readlnOrNull() ?: throw IllegalArgumentException("이름을 입력해주세요.")
        return names.split(COMMA_SEPARATOR)
    }

    fun readDrawMore(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readlnOrNull()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("y 또는 n을 입력해주세요.")
        }
    }
}

object ResultView {
    private const val COMMA_SEPARATOR = ", "
    private const val NEW_LINE = "\n"

    fun printPlayer(player: PlayerVO) {
        println(playerText(player))
    }

    fun printCardHands(players: List<PlayerVO>) {
        val namesText = players.joinToString(COMMA_SEPARATOR) { it.name }
        val playersText = players.joinToString(NEW_LINE, transform = ::playerText)
        println(
            """
            |${namesText}에게 2장의 카드를 나누었습니다
            |$playersText
            |""".trimMargin()
        )
    }

    fun printPlayerScores(players: List<PlayerScoreVO>) {
        val playerScoresText = players.joinToString(NEW_LINE) {
            "${playerText(it.playerVO)} - 결과:${it.score}"
        }
        println(playerScoresText)
    }

    private fun playerText(player: PlayerVO): String {
        val cards = player.cards.joinToString(COMMA_SEPARATOR) { "${it.denomination.symbol}${it.suit.name}" }
        return "${player.name}카드: $cards"
    }
}
