package blackjack.presentation

import blackjack.core.Name
import blackjack.core.Player

object InputView {
    fun getNames(): Set<Name> {
        println(STR_INPUT_NAME)
        val names = readlnOrNull() ?: throw IllegalArgumentException(ERROR_INVALID_STR)
        return names.split(",").map { Name(it) }.toSet()
    }

    fun getCard(player: Player): Boolean {
        println(STR_INPUT_CARD.format(player.name))

        val command = readlnOrNull() ?: throw IllegalArgumentException(ERROR_INVALID_STR)
        return when (command.trim()) {
            STR_YES -> true
            STR_NO -> false
            else -> throw IllegalArgumentException(ERROR_INVALID_STR)
        }
    }

    private const val STR_INPUT_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val STR_INPUT_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val STR_YES = "y"
    private const val STR_NO = "n"

    private const val ERROR_INVALID_STR = "잘못된 값이 입력되었습니다."
}
