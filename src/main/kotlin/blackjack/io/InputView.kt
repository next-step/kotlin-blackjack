package blackjack.io

import blackjack.domain.User
import blackjack.domain.Users

object InputView {
    private const val USER_NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INVALID_INPUT_ERROR_MESSAGE = "올바르지 않은 입력입니다."

    private const val DELIMITER = ","

    fun getUsers(): Users {
        println(USER_NAME_INPUT_MESSAGE)
        val nameList = readlnOrNull()?.split(DELIMITER) ?: throw IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE)
        return Users(nameList.map { User(it) })
    }
}
