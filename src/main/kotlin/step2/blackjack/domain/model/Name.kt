package step2.blackjack.domain.model

@JvmInline
value class Name(val name: String) {

    init {
        require(name.isNotBlank()) {
            "이름이 비어 있거나 공백, 개행, 탭이동 등의 문자로만 이루어 지면 안됩니다."
        }
    }

    override fun toString(): String = name

    companion object {
        fun from(name: String) = Name(name)
    }
}
