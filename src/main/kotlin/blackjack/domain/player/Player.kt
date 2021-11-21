package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.player.state.PlayerState
import blackjack.domain.player.state.Hands
import blackjack.domain.player.state.Started
import blackjack.ui.Command

data class Player(
    private val _name: Name,
    private val playerState: PlayerState = Started(),
) {
    val name: String
        get() = _name.name

    val hands: Hands
        get() = playerState.hands

    operator fun plus(extraCards: List<Card>): Player {
        return Player(_name, playerState.plus(extraCards))
    }

    fun isFinished(): Boolean = playerState.isFinished()

    fun continuePlay(commandString: String): Player {
        val command = Command.values(commandString)
        if (command.nextDraw) {
            return this
        }
        return Player(_name, playerState.stay())
    }

    companion object {
        fun fromName(name: String): Player = fromName(Name(name))
        private fun fromName(name: Name): Player = Player(name)
    }
}
