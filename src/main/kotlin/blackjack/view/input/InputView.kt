package blackjack.view.input

import java.lang.IllegalArgumentException

object InputView {
    private const val PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

    fun playerNames(): List<String> {
        println(PLAYER_NAMES_MESSAGE)
        return readInput().let(InputParser::intoPlayerNames)
    }

    private fun readInput(): String =
        readlnOrNull() ?: throw IllegalArgumentException("입력 값이 없습니다")
}
