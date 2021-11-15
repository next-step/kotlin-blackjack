package blackjack.domain.state

sealed class PlayingState {
    abstract fun isFinish(): Boolean
}

object Running : PlayingState() {
    override fun isFinish(): Boolean = false
}

object End : PlayingState() {
    override fun isFinish(): Boolean = true
}
