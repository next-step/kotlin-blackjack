package blackjack.domain.state

import blackjack.domain.Cards

class Bust(override val cards: Cards) : Finished()
