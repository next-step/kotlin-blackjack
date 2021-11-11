package blackjack

@JvmInline
value class Name(val name: String?) {
    init {
        require(!name.isNullOrBlank()) { BLANK_NOT_ALLOWED }
    }

    companion object {
        const val BLANK_NOT_ALLOWED = "이름에 빈 값이 들어갈 수 없습니다"
    }

    override fun toString(): String {
        return name.toString()
    }
}
