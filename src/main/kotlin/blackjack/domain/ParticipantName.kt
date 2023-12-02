package blackjack.domain

@JvmInline
value class ParticipantName(
    val value: String
) {
    init {
        validateLength(value)
    }

    private fun validateLength(name: String) {
        require(name.length >= MIN_NAME_LENGTH) {
            "이름은 최소 ${MIN_NAME_LENGTH}글자 이상이어야 합니다."
        }
    }

    companion object {
        private const val MIN_NAME_LENGTH = 1
    }
}
