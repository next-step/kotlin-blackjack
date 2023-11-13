package blackjack.domain

@JvmInline
value class Nickname(val value: String) {

    init {
        require(value.length in 1..10) { "닉네임은 1 ~ 10자만 가능합니다." }
    }
}
