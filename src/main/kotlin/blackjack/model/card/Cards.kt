package blackjack.model.card

import blackjack.model.status.Score

class Cards(
    private val _cards: MutableSet<Card> = mutableSetOf()
) {
    val dummy: Set<Card> = _cards

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun addCards(cardsDummy: Cards) {
        this._cards.addAll(cardsDummy.dummy)
    }

    fun getScore(): Score {
        var sum = getSumScore()
        if (isContainAce()) {
            sum = getAceSum(sum)
        }
        return Score(sum)
    }

    fun canMoreCard() = Score(getSumScore()).isBurst().not()

    private fun getAceSum(sum: Int) =
        if (sum + ACE_GAP > Score.BLACKJACK) {
            sum
        } else {
            sum + ACE_GAP
        }

    private fun getSumScore() = dummy.sumBy(Card::getScore)

    private fun isContainAce() = dummy.any { it.isAce() }

    override fun toString() = dummy.joinToString { it.toString() }

    companion object {
        const val ACE_GAP = 10
    }
}
