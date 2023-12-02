package blackjack.domain.state

import blackjack.domain.card.Hands

class Blackjack(override val rate: Double = 1.5, override val hands: Hands) : Finished()
