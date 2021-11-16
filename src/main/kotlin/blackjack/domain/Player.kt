package blackjack.domain

import blackjack.domain.card.PlayingCards
import blackjack.domain.state.PlayingState
import blackjack.domain.state.Running

class Player(
    private val name: Name,
    private var playingState: PlayingState = Running,
    private val playingCards: PlayingCards = PlayingCards.initialize(),
)
