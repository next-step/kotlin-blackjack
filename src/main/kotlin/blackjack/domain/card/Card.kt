package blackjack.domain.card

data class Card(val pattern: CardPattern, val number: CardNumber) {
    override fun toString(): String {
        return "${number.displayName}${pattern.displayName}"
    }
}

enum class CardPattern(val displayName: String) {
    Heart("하트"),
    Spade("스페이드"),
    Diamond("다이아"),
    Club("클로버"),
}

enum class CardNumber(val displayName: String, val value: Int) {
    A("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    companion object {
        val COUNT = CardNumber.values().count()
    }
}

class Cards {
    private val _cards: MutableList<Card> = mutableListOf()
    fun toList() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getScore(): Score {
        val minScoreValue = _cards.sumOf { card -> card.number.value }
        val maxScoreValue = minScoreValue + if (hasAce()) ACE_BONUS_POINT else 0

        val finalScore = if(minScoreValue >= BLACK_JACK_POINT) minScoreValue else maxScoreValue
        return Score(finalScore, BLACK_JACK_POINT - finalScore)
    }

    private fun hasAce(): Boolean = _cards.any { card -> card.number == CardNumber.A && card.pattern == CardPattern.Spade }

    data class Score(val value: Int, val diff: Int)

    companion object {
        const val BLACK_JACK_POINT = 21
        private const val ACE_BONUS_POINT = 10
    }
}
