package blackject.model.card

/**
 * 카드 전체를 관리하는 클래스
 * */
object CardsDeck {
    const val NUMBER_INIT_CARD = 2
    const val NUMBER_ONE_TIME = 1
    val originCardTotalList: List<Card> = CardType
        .values()
        .flatMap { getCardNumberList(it) }
        .toSet()
        .toList()

    private val mutableCardList = originCardTotalList.toMutableList()

    fun takeCard(takeNUmber: Int): List<Card> {
        val cards = mutableCardList
            .shuffled()
            .take(takeNUmber)
        mutableCardList.removeAll(cards)
        return cards
    }

    private fun getCardNumberList(cardType: CardType): List<Card> = CardNumber
        .values()
        .asSequence()
        .map { Card(cardType, it) }
        .toList()
}
