package blackjack.view.input

import blackjack.view.model.PlayerNameModel
import java.lang.IllegalArgumentException

object InputView {
    private const val PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val PLAYER_ACTION_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun playerNames(): List<String> {
        println(PLAYER_NAMES_MESSAGE)
        return readInput().let(InputParser::intoPlayerNames)
    }

    fun playerAction(player: PlayerNameModel): String {
        println(PLAYER_ACTION_MESSAGE.format(player.name))
        return readInput()
    }

    private fun readInput(): String =
        readlnOrNull() ?: throw IllegalArgumentException("입력 값이 없습니다")
}
