package blackjack.domain

sealed class PlayerState {

    abstract fun isDone(): Boolean
    sealed class Play : PlayerState() {
        override fun isDone() = false

        object Idle : Play()
        object Hit : Play()
    }

    sealed class Done : PlayerState() {
        override fun isDone() = true

        object Stay : Done()
        object Burst : Done()
        object BlackJack : Done()
    }
}
