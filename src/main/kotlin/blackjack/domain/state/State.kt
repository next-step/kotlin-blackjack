package blackjack.domain.state

import blackjack.domain.card.PlayingCards

abstract class State(val playingCards: PlayingCards)
