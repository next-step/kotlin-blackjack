package blackjack.domain.player

import blackjack.domain.card.CardHold

class Dealer(
    override val name: String = "딜러",
    override val cardHold: CardHold = CardHold()
) : Player {
    override fun canDraw(): Boolean {
        return cardHold.getCardsTotalSize() <= 2 && cardHold.getTotalPoints() <= 16
    }
}
