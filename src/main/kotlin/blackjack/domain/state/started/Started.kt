package blackjack.domain.state.started

import blackjack.domain.Cards
import blackjack.domain.state.State

abstract class Started(
    protected val cards: Cards
) : State
