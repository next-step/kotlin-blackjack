package blackjack.view

import blackjack.constant.ErrorMessages
import blackjack.constant.Messages

/**
 * 입력을 받는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
object InputView {
    fun getPlayersName(readStringValue: () -> String? = { readlnOrNull() }): List<String> {
        println(Messages.WRITE_PLAYERS_NAME)
        val input = readStringValue()
        require(!input.isNullOrBlank()) { ErrorMessages.INPUT_IS_NULL_OR_BLANK }
        return input.split(",")
    }

    fun getYesOrNo(readStringValue: () -> String? = { readlnOrNull() }): Boolean {
        val input = readStringValue()?.lowercase()
        require(!input.isNullOrBlank()) { ErrorMessages.INPUT_IS_NULL_OR_BLANK }
        require(input == "y" || input == "n") { ErrorMessages.INPUT_IS_NOT_YES_OR_NO }
        return input == "y"
    }
}
