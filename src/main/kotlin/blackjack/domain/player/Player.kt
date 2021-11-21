package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.player.state.GameState
import blackjack.domain.player.state.Hands
import blackjack.domain.player.state.Started
import blackjack.ui.Command

data class Player(
    private val _name: Name,
    private val gameState: GameState = Started(),
) {
    val name: String
        get() = _name.name

    val hands: Hands
        get() = gameState.hands

    operator fun plus(extraCards: List<Card>): Player {
        return Player(_name, gameState.plus(extraCards))
    }

    fun isFinished(): Boolean = gameState.isFinished()

    fun continuePlay(commandString: String): Player {
        val command = Command.values(commandString)
        if (command.nextDraw) {
            return this
        }
        return Player(_name, gameState.stay())
    }

    companion object {
        fun fromName(name: String): Player = fromName(Name(name))
        private fun fromName(name: Name): Player = Player(name)
    }
}
