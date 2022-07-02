package blackjack.model.candidate

@JvmInline
value class CandidateName(
    val name: String
) {

    init {
        validateNotEmpty(name)
    }

    private fun validateNotEmpty(name: String) = require(name.isNotBlank()) { "참가자 이름이 공백일 수 없습니다." }
}
