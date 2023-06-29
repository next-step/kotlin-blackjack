package blackjack.domain.game

@JvmInline
value class Money(val value: Int) {

    init {
        require(value > 0) {
            "money must be positive"
        }
    }
}
