package blackjack.io

import blackjack.domain.Users

object InputView {
    private const val USER_NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val CHECK_USER_HIT_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val INVALID_INPUT_ERROR_MESSAGE = "올바르지 않은 입력입니다."

    private const val DELIMITER = ","

    fun getUsers(): Users {
        println(USER_NAME_INPUT_MESSAGE)
        val nameList = requireNotNull(readlnOrNull()?.split(DELIMITER)) { INVALID_INPUT_ERROR_MESSAGE }

        return Users(nameList)
    }

    fun checkHit(name: String): Boolean {
        println(CHECK_USER_HIT_MESSAGE.format(name))
        return when (readlnOrNull()) {
            "y", "Y" -> true
            "n", "N" -> false
            else -> throw throw IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE)
        }
    }
}
