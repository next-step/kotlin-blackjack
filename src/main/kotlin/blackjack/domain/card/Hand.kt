package blackjack.domain.card

class Hand {
    private val cardList = mutableListOf<Card>()

    fun addCardToHand(card: Card): Boolean {
        if (isAddAble())
            return cardList.add(card)
        return false
    }

    fun isAddAble(): Boolean = minValueOfHand() < VALUE_OF_WIN && !isBlackJackHand()

    fun getCards() = cardList.toList()

    private fun minValueOfHand(): Int = cardList.sumOf { it.cardValue }

    private fun isBlackJackHand(): Boolean {
        val numberOfAces = cardList
            .filter { it.cardValue == 1 }
            .size
        val sumOfCardWithoutAce = cardList
            .filter { it.cardValue != 1 }
            .sumOf { it.cardValue }
        val leftValueForAces = VALUE_OF_WIN - sumOfCardWithoutAce
        val actualNeedAces = leftValueForAces % 11 + leftValueForAces / 11

        return actualNeedAces == numberOfAces
    }

    companion object {
        private const val VALUE_OF_WIN = 21
    }
}
