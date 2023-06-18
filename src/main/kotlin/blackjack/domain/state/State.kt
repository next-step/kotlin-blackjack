package blackjack.domain.state

import blackjack.domain.card.PlayingCards

sealed class State(val playingCards: PlayingCards)
