package blackjack.domain.model

@JvmInline
value class Name(val name: String) {

    init {
        require(name.isNotBlank()) {
            "이름이 비어 있거나 공백, 개행, 탭이동 등의 문자로만 이루어 지면 안됩니다."
        }
        require(name.length in MIN_LENGTH..MAX_LENGTH) {
            "이름은 1 ~ 10자 이내이여야 한다."
        }
    }

    override fun toString(): String = name

    companion object {
        private const val MIN_LENGTH = 1
        private const val MAX_LENGTH = 10
        fun from(name: String) = Name(name)
    }
}
