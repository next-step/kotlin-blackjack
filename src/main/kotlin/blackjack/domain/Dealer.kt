package blackjack.domain

import blackjack.domain.enums.Condition
import blackjack.domain.enums.MatchResult

class Dealer(
    name: String = "딜러",
    cards: Cards,
    condition: Condition = Condition.STAY,
    val deck: Deck,
) : Participant(name, cards, condition) {

    init {
        val score = cards.calculateScore()
        initCondition(score)
    }

    private fun initCondition(score: Score) {
        if (score.compareTo(Score.STANDARD_CARD_SCORE) == -1 || score.compareTo(Score.STANDARD_CARD_SCORE) == 0) {
            this.condition = Condition.PLAY
        }
    }

    override fun hit(card: Card) {
        super.hit(card)

        val score = cards.calculateScore()
        changeCondition(score)
    }

    private fun changeCondition(score: Score) {
        if (score.compareTo(Score.STANDARD_CARD_SCORE) == 1) {
            this.condition = Condition.STAY
        } else if (score.compareTo(Score.BLACK_JACK_SCORE) == 1) {
            this.condition = Condition.BUST
        }
    }

    fun draw(count: Int): Cards {
        return deck.drawCard(count)
    }

    fun currentCondition(): Condition {
        return this.condition
    }

    fun determineResult(otherScore: Score): MatchResult {
        val score = cards.calculateScore()

        return when {
            score.value > otherScore.value -> MatchResult.WIN
            score.value < otherScore.value -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }

    companion object {
        const val ONE_DRAW_COUNT = 1
    }
}
