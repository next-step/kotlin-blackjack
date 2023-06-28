package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.rule.Score

class Dealer(override val cardHold: CardHold = CardHold()) : GameMember {
    override val name: String = "딜러"

    override fun canDraw(): Boolean {
        return cardHold.getCardsTotalSize() <= 2 && cardHold.getTotalPoints() <= 16
    }

    fun compareScore(other: GamePlayer): Score {
        if (other.getPoints() > 21) return Score().win()
        if (getPoints() > 21) return Score().lose()
        return when {
            this.getPoints() > other.getPoints() -> Score().win()
            this.getPoints() < other.getPoints() -> Score().lose()
            this.getCardHoldSize() < other.getCardHoldSize() -> Score().win()
            else -> Score().draw()
        }
    }
}
