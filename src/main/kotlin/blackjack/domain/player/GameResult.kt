package blackjack.domain.player

enum class GameResult {
    WIN {
        override fun toString() = "승"
    },
    LOSE {
        override fun toString() = "패"
    },
    PUSH {
        override fun toString() = "무"
    }
}
