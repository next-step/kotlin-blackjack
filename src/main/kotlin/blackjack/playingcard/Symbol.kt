package blackjack.playingcard

enum class Symbol(val initial: String, private val value: Value) {
    TWO("2", Value(2)),
    THREE("3", Value(3)),
    FOUR("4", Value(4)),
    FIVE("5", Value(5)),
    SIX("6", Value(6)),
    SEVEN("7", Value(7)),
    EIGHT("8", Value(8)),
    NINE("9", Value(9)),
    TEN("10", Value(10)),
    JACK("J", Value(10)),
    QUEEN("Q", Value(10)),
    KING("K", Value(10)),
    ACE("A", Value(11)) {
        override fun valueBy(sumOthers: Value): Value {
            if (super.value + sumOthers > Value.BLACKJACK_THRESHOLD) {
                return ACE_HARD
            }

            return super.value
        }
    };

    open fun valueBy(sumOthers: Value): Value {
        return this.value
    }

    companion object {
        private val ACE_HARD = Value(1)
    }
}
