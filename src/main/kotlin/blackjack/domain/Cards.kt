package blackjack.domain

class Cards private constructor(
    private val _cards: MutableList<Card>
) {
    val cards: List<Card>
        get() = _cards.toList()

    operator fun plus(card: Card) {
        _cards.add(card)
    }

    fun isBust(): Boolean {
        return isBust(calculate())
    }

    private fun isBust(score: Score): Boolean {
        if (score.bust) return true
        return score.value > GameConfig.BUST_CONDITION
    }

    fun calculate(): Score = CardScoreCalculator.calculate(_cards)

    companion object {
        fun from(cards: List<Card>): Cards {
            return Cards(cards.toMutableList())
        }
    }
}
