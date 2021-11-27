package blackjack.view

import blackjack.domain.Player

object InputView {

    private const val GET_PLAYERS_QUESTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val COMMA_DELIMITER = ","
    private const val INVALID_INPUT_MESSAGE = "입력값이 올바르지 않습니다."

    fun inputPlayerNames(): List<String> {
        println(GET_PLAYERS_QUESTION)
        return readLine()
            ?.split(COMMA_DELIMITER)
            ?.map { it.trim() }
            ?: throw IllegalArgumentException(
                INVALID_INPUT_MESSAGE
            )
    }

    fun acceptMoreCard(player: Player): String {
        println("${player.name.name}은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readLine() ?: throw IllegalArgumentException(INVALID_INPUT_MESSAGE)
    }
}
