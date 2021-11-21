package blackjack.state

import blackjack.model.Cards

abstract class Started(override val cards: Cards) : State
