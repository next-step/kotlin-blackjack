package blackjack.participant

sealed interface Result {
    object Win : Result
    object Lose : Result
    class DealerResult(val win: Int, val lose: Int) : Result
}
