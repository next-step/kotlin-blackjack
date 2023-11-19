package blackjack.domain

@JvmInline
value class Nickname(
    val value: String
) {
    init {
        require(value.length in 1..5) { "닉네임은 3글자 이상 10글자 이하만 가능합니다." }
    }
}
