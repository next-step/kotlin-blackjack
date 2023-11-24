package blackjack.domain

import blackjack.domain.rule.ScoringRule

class Player(val name: String, private val scoringRule: ScoringRule) {
    private val _cards: DrewCards = DrewCards(scoringRule)
    val cards: List<Card> get() = _cards.cards

    init {
        require(name.length <= 10) { "이름은 10자를 넘을 수 없습니다." }
    }

    var totalScore: Int = 0
        get() = _cards.totalScore
        private set

    fun draw(deck: Deck) {
        this._cards.add(deck.draw())
    }

    fun canDraw(): Boolean {
        return scoringRule.isOverThreshold(totalScore).not()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
