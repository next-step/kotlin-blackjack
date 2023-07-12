package blackjack.domain

import blackjack.domain.enums.Condition

abstract class Participant(
    val name: String,
    val cards: Cards,
    condition: Condition
) {
    var condition: Condition = condition
        protected set

    open fun hit(card: Card) {
        cards.append(card)
    }

    open fun checkBlackjack() {
        if (cards.calculateScore().isBlackjack()) {
            this.condition = Condition.BLACKJACK
        }
    }
}
