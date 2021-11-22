package blackject.model.card

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

    fun getResultNumber(maxInt: Int, exceptCard: CardNumber): Int {
        val cardTotal = getTotalMinCount(_cardList)
        val count = _cardList.filter { it.number == exceptCard }.size
        if (count == 0) return cardTotal

        val exceptRestNumber = CardNumber.getNumberMaxValue(exceptCard) - CardNumber.getNumberMinValue(exceptCard)
        if ((maxInt - cardTotal) < exceptRestNumber) return cardTotal
        return cardTotal.plus(exceptRestNumber)
    }

    // fun isBlackjack(): Boolean =
    //
    // fun isBust(): Boolean = sum() > TWENTY_ONE

    companion object {
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
