package blackjack.domain.player

import blackjack.domain.card.CardHold

class PlayerImpl(
    override val name: String,
    override val cardHold: CardHold = CardHold()
) : Player
