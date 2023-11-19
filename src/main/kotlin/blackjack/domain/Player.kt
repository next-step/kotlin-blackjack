package blackjack.domain

import blackjack.domain.rule.ScoringRule

class Player(val name: String, private val scoringRule: ScoringRule) {
    val cards: MutableList<Card> = mutableListOf()

    init {
        require(name.length <= 10) { "이름은 10자를 넘을 수 없습니다." }
    }

    var totalScore: Int = 0
        get() = scoring()
        private set

    fun draw(deck: Deck) {
        cards.add(deck.draw())
    }

    fun canDraw(): Boolean {
        return scoringRule.isOverThreshold(totalScore).not()
    }

    private fun scoring(): Int {
        return scoringRule.sumAll(cards)
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
