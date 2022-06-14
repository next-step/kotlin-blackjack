package blackjack.domain

enum class ResultStatus {
    Win, Lose, Draw;

    operator fun not(): ResultStatus {
        return when (this) {
            Win -> Lose
            Lose -> Win
            Draw -> Draw
        }
    }
}
