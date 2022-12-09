package blackjack.domain

const val BLACKJACK_SCORE = 21

class Cards(list: List<Card> = listOf()) {
    private val _list: MutableList<Card> = list.toMutableList()

    val list: List<Card>
        get() = _list.sortedBy { it.number.ordinal }

    override fun toString(): String = list.joinToString()

    fun add(card: Card) {
        _list.add(card)
    }

    fun count(): Int = list.size

    fun countingCard(): Int {
        var score = _list.sumOf { it.number.score }

        score += countingMaxAceCard(score, _list.count { it.number == CardNumber.ACE })
        return score
    }

    private fun countingMaxAceCard(score: Int, countOfAceCard: Int): Int {
        if (countOfAceCard == 0) {
            return 0
        }

        var aceScore = 0
        repeat(countOfAceCard) {
            val tmp = score + aceScore - ACE_CARD_MIN_SCORE + ACE_CARD_MAX_SCORE
            if (tmp <= BLACKJACK_SCORE) {
                aceScore = ACE_CARD_MAX_SCORE - ACE_CARD_MIN_SCORE
            }
        }
        return aceScore
    }
}
