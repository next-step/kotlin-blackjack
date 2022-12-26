package blackjack.domain.participant.state.result

sealed class Result {
    object Win : Result()
    object Lose : Result()
    object Draw : Result()

    override fun toString(): String {
        return when (this) {
            is Win -> "승"
            is Lose -> "패"
            is Draw -> "무"
        }
    }
}
