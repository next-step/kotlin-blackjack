package blackjack.exception.enums

import blackjack.exception.ErrorStatus

enum class CardDeckError(private val errorMessage: String) : ErrorStatus {

    CARD_EMPTY("모든 카드를 소모 하였습니다.");

    override fun getErrorMessage(): String {
        return errorMessage
    }
}
