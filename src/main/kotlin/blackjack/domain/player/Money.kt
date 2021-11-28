package blackjack.domain.player

@JvmInline
value class Money(private val money: Int = 0) {
    init {
        if (money < 0) {
            throw IllegalArgumentException()
        }
    }
}
