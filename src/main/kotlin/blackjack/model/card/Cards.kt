package blackjack.model.card

class Cards {
    private val cards = mutableSetOf<Card>()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getScore(): Int {
        val sum = getSumScore()
        return if (isContainAce()) {
            getAceSum(sum)
        } else {
            sum
        }
    }

    private fun getAceSum(sum: Int) =
        if (sum + ACE_GAP > BLACKJACK_SCORE) {
            sum
        } else {
            sum + ACE_GAP
        }

    private fun getSumScore() = cards.sumBy(Card::getScore)

    private fun isContainAce() = cards.any { it.isAce() }

    override fun toString() = cards.joinToString { it.toString() }

    companion object {
        const val BLACKJACK_SCORE = 21
        const val ACE_GAP = 10
    }
}
