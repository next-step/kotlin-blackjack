package blackjack.domain.state

import blackjack.domain.Cards

class Stand(override val cards: Cards) : Finished()
