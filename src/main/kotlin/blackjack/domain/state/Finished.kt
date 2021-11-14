package blackjack.domain.state

abstract class Finished : State {

    override fun isFinished(): Boolean = true
}
