package blackjack.domain

import blackjack.domain.Score.Companion.BLACK_JACK_SCORE
import blackjack.domain.Score.Companion.STANDARD_CARD_SCORE
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
        if (score < STANDARD_CARD_SCORE || score == STANDARD_CARD_SCORE) {
            this.condition = Condition.PLAY
        }
    }

    override fun hit(card: Card) {
        super.hit(card)

        val score = cards.calculateScore()
        changeCondition(score)
    }

    private fun changeCondition(score: Score) {
        this.condition = condition.from(score)
    }

    fun draw(count: Int): Cards {
        return deck.drawCard(count)
    }

    fun currentCondition(): Condition {
        return this.condition
    }

    fun determineResult(other: Player): MatchResult {
        val score = cards.calculateScore()
        val otherScore = other.cards.calculateScore()

        return when {
            score > otherScore -> MatchResult.WIN
            score < otherScore -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }

    companion object {
        const val ONE_DRAW_COUNT = 1
    }
}
