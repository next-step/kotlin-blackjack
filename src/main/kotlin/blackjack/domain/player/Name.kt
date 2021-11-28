package blackjack.domain.player

@JvmInline
value class Name(val value: String) {

    companion object {
        private fun verify(value: String) {
            require(!value.isNullOrEmpty()) { "이름은 null 또는 빈문자열일 수 없습니다." }
        }

        fun from(value: String): Name {
            verify(value)
            return Name(value)
        }
    }
}
