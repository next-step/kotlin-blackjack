package blackjack.domain

import blackjack.domain.enums.Condition

class Dealer(
    val name: String = "딜러",
    val cards: Cards,
    val deck: Deck,
    private var condition: Condition = Condition.STAY
) {

    init {
        var total = 0
        cards.cards.forEach {
            total += it.rank.value
        }
        if( STANDARD_CARD_SCORE >= total) {
            condition = Condition.PLAY
        }
    }

    fun draw(count: Int): Cards {
        return deck.drawCard(count)
    }

    fun currentCondition(): Condition {
        return this.condition
    }

    companion object {
        private const val STANDARD_CARD_SCORE = 16
    }
}
