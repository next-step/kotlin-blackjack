package blackjack.participant

sealed interface Result {
    object Win : Result
    object Lose : Result
}
