package blackjack.domain

import blackjack.domain.enums.Condition

class Dealer(
    name: String = "딜러",
    cards: Cards,
    condition: Condition = Condition.STAY,
    val deck: Deck,
) : Participant(name, cards, condition) {

    init {
        val score = cards.calculateScore()

        if (score.isScoreBelowStandard()) {
            this.condition = Condition.PLAY
        }
    }

    override fun hit(card: Card) {
        super.hit(card)

        val score = cards.calculateScore()
        changeCondition(score)
    }

    private fun changeCondition(score: Score) {
        if (score.isScoreAboveStandard()) {
            this.condition = Condition.STAY
        } else if (score.isScoreAboveBlackjack()) {
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
    }
}
