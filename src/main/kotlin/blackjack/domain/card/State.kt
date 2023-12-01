package blackjack.domain.card

enum class State(val isFinished: Boolean) {

    BEGIN(false) {
        override fun profit(bet: Int): Int {
            return 0
        }
    },
    HIT(false) {
        override fun profit(bet: Int): Int {
            return 0
        }
    },
    STAY(true) {
        override fun profit(bet: Int): Int {
            return bet
        }
    },
    BLACKJACK(true) {
        override fun profit(bet: Int): Int {
            return (bet * 1.5).toInt()
        }
    },
    BUST(true) {
        override fun profit(bet: Int): Int {
            return -bet
        }
    };

    abstract fun profit(bet: Int): Int
}
