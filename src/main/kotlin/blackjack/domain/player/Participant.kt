package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.rule.ScoringRule

class Participant(override val name: String, private val scoringRule: ScoringRule) : Player(name, scoringRule) {

    init {
        require(name.length <= 10) { "이름은 10자를 넘을 수 없습니다." }
    }

    override fun draw(deck: Deck) {
        this._cards.add(deck.draw())
    }

    override fun canDraw(): Boolean {
        return scoringRule.isOverThreshold(totalScore, DefaultScoringRule.THRESHOLD_SCORE).not()
    }
}