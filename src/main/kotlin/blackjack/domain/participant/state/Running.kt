package blackjack.domain.participant.state

sealed class Running(hand: Hand) : AfterInit(hand) {

    override fun isFinished(): Boolean = false
}
