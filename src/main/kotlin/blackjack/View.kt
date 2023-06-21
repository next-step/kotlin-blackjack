package blackjack

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

    fun printPlayer(player: PlayerVO) {
        val cards = player.cards.joinToString(COMMA_SEPARATOR) { "${it.denomination.symbol}${it.suit.name}" }
        println("${player.name}카드: $cards")
    }

    fun printCardHands(players: List<PlayerVO>) {
        val namesText = players.joinToString(COMMA_SEPARATOR) { it.name }
        println("${namesText}에게 2장의 카드를 나누었습니다")
        players.forEach(::printPlayer)
    }
}
