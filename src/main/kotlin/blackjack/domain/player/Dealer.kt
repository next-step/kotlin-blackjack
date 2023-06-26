package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.rule.Score

class Dealer(
    override val name: String = "딜러",
    override val cardHold: CardHold = CardHold()
) : Player {
    override fun canDraw(): Boolean {
        return cardHold.getCardsTotalSize() <= 2 && cardHold.getTotalPoints() <= 16
    }

    override fun compareScore(other: Player): Score {
        if (cardHold.getTotalPoints() >= 21) return Score(1, 0, 0)
        return super.compareScore(other)
    }
}
