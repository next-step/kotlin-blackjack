package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.player.state.End
import blackjack.domain.player.state.PlayingState
import blackjack.domain.player.state.Running
import blackjack.ui.Command

data class Player(
    private val _name: Name,
    val hands: Hands = Hands.EMPTY,
    private val playingState: PlayingState = Running,
) {
    val name: String
        get() = _name.name

    operator fun plus(extraCards: List<Card>): Player {
        val changedHands = hands + extraCards
        val sumScore = changedHands.score()
        if (sumScore.isOverMaximum()) {
            return Player(_name, changedHands, End)
        }
        return Player(_name, changedHands, playingState)
    }

    fun isFinished(): Boolean = playingState.isFinish()

    fun continuePlay(command: String): Player = continuePlay(Command.values(command))

    private fun continuePlay(command: Command): Player = Player(_name, hands, PlayingState.of(command.nextDraw))

    companion object {
        fun fromName(name: String): Player = fromName(Name(name))
        private fun fromName(name: Name): Player = Player(name)
    }
}
