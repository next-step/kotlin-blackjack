package blackject.model.card

import blackject.model.card.CardNumber.Companion.EXCEPT_NUMBER

/**
 * 카드 리스트
 * */
@JvmInline
value class Cards(
    private val _cardList: MutableList<Card> = mutableListOf()
) {
    val cardList: List<Card>
        get() = _cardList.toList()

    fun addCard(cards: List<Card>) {
        _cardList.addAll(0, cards)
    }

    fun getResultNumber(): Int {
        val cardTotal = getTotalMinCount(_cardList)
        val count = _cardList.filter { it.number == EXCEPT_NUMBER }.size
        if (count == 0) return cardTotal

        val exceptRestNumber = CardNumber.getNumberMaxValue(EXCEPT_NUMBER) - CardNumber.getNumberMinValue(EXCEPT_NUMBER)
        if ((BLACK_JACK_SUM - cardTotal) < exceptRestNumber) return cardTotal
        return cardTotal.plus(exceptRestNumber)
    }

    fun isBlackjack(sum: Int): Boolean = _cardList.size == 2 && sum == BLACK_JACK_SUM

    fun isBust(sum: Int): Boolean = sum > BLACK_JACK_SUM

    companion object {
        const val BLACK_JACK_SUM = 21
        fun getTotalMinCount(cardList: List<Card>): Int =
            cardList
                .map { it.number }
                .sumOf { CardNumber.getNumberMinValue(it) }

        fun getTotalMaxCount(cardList: List<Card>): Int =
            cardList
                .map { it.number }
                .sumOf { CardNumber.getNumberMaxValue(it) }
    }
}
