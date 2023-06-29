package blackjack.domain

import blackjack.domain.enums.Condition

open class Participant(
    val name: String,
    val cards: Cards,
    protected open val condition: Condition
) {

    open fun hit(card: Card) {
        cards.append(card)
    }
}
