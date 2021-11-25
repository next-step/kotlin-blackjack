package blackjack.domain

@JvmInline
value class Name private constructor(val name: String) {

    init {
        require(name.isNotBlank()) { NAME_MUST_NOT_BLANK_MESSAGE }
    }

    companion object {
        const val NAME_MUST_NOT_BLANK_MESSAGE = "이름은 공백일 수 없습니다."

        fun from(name: String): Name {
            return Name(name.trim())
        }
    }
}
