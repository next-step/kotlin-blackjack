package blackjack.domain.exception.enums

import blackjack.domain.exception.ErrorStatus

enum class InputError(private val errorMessage: String) : ErrorStatus {

    NOT_INPUT("값이 비어있을수 없습니다. 값을 넣어주세요.");

    override fun getErrorMessage(): String {
        return errorMessage
    }
}
