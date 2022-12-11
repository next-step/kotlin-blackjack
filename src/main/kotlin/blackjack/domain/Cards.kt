package blackjack.domain

const val BLACKJACK_SCORE = 21

class Cards(val list: List<Card> = listOf()) {

    override fun toString(): String = list.joinToString()

    fun add(card: Card): Cards = Cards(this.list + card)

    fun addAll(cards: Cards): Cards = Cards(this.list + cards.list)

    fun count(): Int = list.size

    fun countingCard(): Int {
        var score = list.sumOf { it.number.score }

        score += countingMaxAceCard(score, list.count { it.number == CardNumber.ACE })
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

    companion object {
        fun empty() = Cards(emptyList())
    }
}
