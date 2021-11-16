package blackjack.domain.player

import blackjack.domain.state.PlayingState
import blackjack.domain.state.Running

class Player(
    private val name: Name,
    private var playingState: PlayingState = Running,
    private val playingCards: PlayingCards = PlayingCards.initialize(),
) {
    companion object {
        fun fromName(name: String): Player = Player(Name(name))
    }
}
