package blackjack.domain

import blackjack.domain.enums.Condition

class Dealer(
    name: String = "딜러",
    cards: Cards,
    condition: Condition = Condition.STAY,
    val deck: Deck,
): Participant(name, cards, condition) {

    init {
        var total = 0
        cards.cards.forEach {
            total += it.rank.value
        }
        if( STANDARD_CARD_SCORE >= total) {
            this.condition = Condition.PLAY
        }
    }

    override fun hit(card: Card) {
        super.hit(card)
        var total = 0
        cards.cards.forEach {
            total += it.rank.value
        }
        if( STANDARD_CARD_SCORE < total) {
            this.condition = Condition.STAY
        } else if (total > BlackjackGame.BLACK_JACK_NUMBER) {
            this.condition = Condition.BUST
        }
    }

    fun draw(count: Int): Cards {
        return deck.drawCard(count)
    }

    fun currentCondition(): Condition {
        return this.condition
    }

    companion object {
        const val ONE_DRAW_COUNT = 1
        const val STANDARD_CARD_SCORE = 16
    }
}
