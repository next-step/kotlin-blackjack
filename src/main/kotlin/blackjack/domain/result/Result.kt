package blackjack.domain.result

enum class Result {
    WIN {
        override fun opposite() = LOSE
    },
    LOSE {
        override fun opposite() = WIN
    },
    DRAW {
        override fun opposite() = DRAW
    };

    abstract fun opposite(): Result
}
