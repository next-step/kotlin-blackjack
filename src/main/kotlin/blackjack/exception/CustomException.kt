package blackjack.exception

sealed interface CustomException {
    data class InvalidInputError(override val message: String? = null) :
        IllegalArgumentException(message ?: "Input 값이 올바르지 않습니다.")
}
