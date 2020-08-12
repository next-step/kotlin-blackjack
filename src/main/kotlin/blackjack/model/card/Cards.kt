package blackjack.model.card

class Cards {
    private val _cards = mutableSetOf<Card>()
    val dummy: Set<Card> = _cards

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun addCards(cardsDummy: Cards) {
        this._cards.addAll(cardsDummy.dummy)
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

    private fun getSumScore() = dummy.sumBy(Card::getScore)

    private fun isContainAce() = dummy.any { it.isAce() }

    override fun toString() = dummy.joinToString { it.toString() }

    companion object {
        const val BLACKJACK_SCORE = 21
        const val ACE_GAP = 10
    }
}
