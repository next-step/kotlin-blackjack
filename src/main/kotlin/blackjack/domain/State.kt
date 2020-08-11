package blackjack.domain

sealed class State {
    object Playing : State()
    object Busted : State()
    object Stand : State()
}
