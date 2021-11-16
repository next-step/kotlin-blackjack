package blackjack.domain.player

import blackjack.domain.Command
import blackjack.domain.playingcard.PlayingCard
import blackjack.domain.state.End
import blackjack.domain.state.PlayingState
import blackjack.domain.state.Running

class Player(
    private val _name: Name,
    private val playingState: PlayingState = Running,
    private val playingCards: PlayingCards = PlayingCards.initialize(),
) {
    val name: String = _name.name

    fun isFinished(): Boolean = playingState.isFinish()

    fun addPlayingCards(extraPlayingCards: List<PlayingCard>): Player {
        val addedPlayingCards: PlayingCards = playingCards.plus(extraPlayingCards)
        val sumScore = addedPlayingCards.sumScore()
        if (sumScore.isOverBlackJack()) {
            return Player(_name, End, addedPlayingCards)
        }
        return Player(_name, playingState, addedPlayingCards)
    }

    fun continuePlayingTheGame(command: String): Player =
        continuePlayingTheGame(Command.values(command))

    private fun continuePlayingTheGame(command: Command): Player =
        Player(_name, PlayingState.of(command.type), playingCards)

    companion object {
        fun fromName(name: String): Player = Player(Name(name))
    }
}
