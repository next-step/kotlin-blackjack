package blackjack.domain.exception.enums

import blackjack.domain.exception.ErrorStatus

enum class CardDeckError(private val errorMessage: String) : ErrorStatus {

    CARD_EMPTY("모든 카드를 소모 하였습니다.");

    override fun getErrorMessage(): String {
        return errorMessage
    }
}
