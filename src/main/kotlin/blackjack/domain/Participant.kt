package blackjack.domain

import blackjack.domain.enums.Condition

open class Participant(
    val name: String,
    val cards: Cards,
    var condition: Condition
) {

    open fun hit(card: Card) {
        this.cards.append(card)
    }
}
