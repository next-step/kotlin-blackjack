package blackjack.domain.player

data class UserName(val name: String) {
    init {
        validateLength()
        validateContainBlank()
    }

    private fun validateLength() {
        require(name.isNotBlank()) { "이름은 공백이 될 수 없습니다." }
        require(name.length < MAX_LENGTH) { "이름은 ${MAX_LENGTH}글자 이상이 될 수 없습니다." }
    }

    private fun validateContainBlank() {
        require(!name.contains(BLANK)) { "이름엔 공백이 포함될 수 없습니다." }
    }

    companion object {
        private const val MAX_LENGTH = 10
        private const val BLANK = " "
    }
}
