package blackject.model.card

/**
 * 카드 전체를 관리하는 클래스
 * */
object CardsDeck {
    const val NUMBER_INIT_CARD = 2
    const val NUMBER_ONE_TIME = 1

    private val originCardTotalList: MutableList<Card> = CardType
        .values()
        .flatMap { getCardNumberList(it) }
        .toSet()
        .toMutableList()

    fun takeCard(takeNUmber: Int): List<Card> {
        val cards = originCardTotalList
            .shuffled()
            .take(takeNUmber)
        originCardTotalList.removeAll(cards)
        return cards
    }

    private fun getCardNumberList(cardType: CardType): List<Card> = CardNumber
        .values()
        .asSequence()
        .map { Card(cardType, it) }
        .toList()
}
