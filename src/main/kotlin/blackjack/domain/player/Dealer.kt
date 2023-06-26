package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.rule.Score

class Dealer(override val cardHold: CardHold = CardHold()) : Player {
    override val name: String = "딜러"

    override fun canDraw(): Boolean {
        return cardHold.getCardsTotalSize() <= 2 && cardHold.getTotalPoints() <= 16
    }

    override fun compareScore(other: Player): Score {
        if (getPoints() >= 21) return Score.init().win() // Bust
        if (other is Dealer) return Score.init() // impossible to compare score between dealer
        return when {
            this.getPoints() > other.getPoints() -> Score.init().win()
            this.getPoints() < other.getPoints() -> Score.init().lose()
            else -> Score.init().draw()
        }
    }

    fun showOnlyOneCard(): String {
        return "${this.name}카드: ${this.cardHold.getAllCards()[0].rank.mark}${this.cardHold.getAllCards()[0].shape.mark}"
    }
}
