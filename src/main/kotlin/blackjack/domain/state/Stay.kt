package blackjack.domain.state

import blackjack.domain.card.Hands

class Stay(override val rate: Double = 1.0, override val hands: Hands) : Finished()
