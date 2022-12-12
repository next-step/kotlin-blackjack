package blackjack.domain

const val BLACKJACK_SCORE = 21

data class Cards(private val list: List<Card> = listOf()) {

    operator fun plus(card: Card): Cards = Cards(this.list + card)

    operator fun plus(cards: Cards): Cards = Cards(this.list + cards.list)

    fun count(): Int = list.size

    fun countingCard(): Int {
        var score = list.sumOf { it.getScore() }

        score += countingMaxAceCard(score, list.count { it.isAce() })
        return score
    }

    private fun countingMaxAceCard(score: Int, countOfAceCard: Int): Int {
        if (countOfAceCard == 0) {
            return 0
        }

        var aceScore = 0
        repeat(countOfAceCard) {
            val tmp = score + aceScore + ACE_CARD_MAX_SCORE - 1
            if (tmp <= BLACKJACK_SCORE) {
                aceScore = ACE_CARD_MAX_SCORE - 1
            }
        }
        return aceScore
    }

    override fun toString(): String = list.joinToString()

    companion object {
        fun empty() = Cards(emptyList())
    }
}
