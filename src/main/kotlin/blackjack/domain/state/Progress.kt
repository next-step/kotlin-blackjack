package blackjack.domain.state

abstract class Progress : State {
    override fun isFinished(): Boolean = false
}
