package blackjack.domain.player

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

    fun addCards(extraPlayingCards: List<PlayingCard>): Player =
        Player(_name, playingState, playingCards.addCards(extraPlayingCards))

    fun end(): Player = Player(_name, End, playingCards)

    companion object {
        fun fromName(name: String): Player = Player(Name(name))
    }
}
