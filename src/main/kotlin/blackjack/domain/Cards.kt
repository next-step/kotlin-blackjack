package blackjack.domain

class Cards(private val cards: MutableList<Card>) : MutableList<Card> by cards {

    fun calculateScore(): Int {
        val aceCount = cards.map { it.rank }.count { it.isAce(it) }
        var score = cards.sumBy { it.rank.value }

        repeat(aceCount) {
            if (score + ACE_ADD_POINT <= BLACKJACK_WINNER_POINT) {
                score += ACE_ADD_POINT
            }
        }

        return score
    }

    companion object {
        const val BLACKJACK_WINNER_POINT = 21
        const val ACE_ADD_POINT = 10
    }
}
