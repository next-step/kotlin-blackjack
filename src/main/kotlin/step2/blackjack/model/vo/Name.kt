package step2.blackjack.model.vo

/**
 * 이름
 * */
@JvmInline
value class Name(val name: String) {
    init {
        require(name.isNotBlank()) {
            "이름이 비어있으면 안됩니다."
        }

        require(name.length in MIN_LENGTH .. MAX_LENGTH) {
            "이름은 ${MIN_LENGTH}이상 ${MAX_LENGTH}이하의 길이여야 합니다."
        }
    }

    override fun toString(): String = name

    companion object {
        private const val MIN_LENGTH = 3
        private const val MAX_LENGTH = 20

        /**
         * 이름 생성
         * */
        fun from(name: String) = Name(name)
    }
}
