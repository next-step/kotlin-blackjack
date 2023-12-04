package blackjack.participant

sealed interface Result {
    class Win : Result
    class Lose : Result
    class DealerResult(val win: Int, val lose: Int) : Result
}
