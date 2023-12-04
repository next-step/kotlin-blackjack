package blackjack.domain.state

import blackjack.domain.Cards

class Blackjack(override val cards: Cards) : Finished()
