package step2.blackjack.model

/**
 * 카드 리스트
 * */
class CardList(cardList: List<Card>) {

    init {
        require(cardList.size == cardList.distinct().size) {
            "카드 리스트에는 동일한 카드가 있으면 안된다."
        }
    }

    private val _cardList: MutableList<Card> = cardList.toMutableList()
    val cardList: List<Card>
        get() = _cardList.toList()

    operator fun plus(card :Card) {
        require(cardList.isEmpty() || !cardList.contains(card)) {
            "카드 리스트에는 동일한 카드가 있으면 안된다."
        }
        _cardList.add(card)
    }

    override fun toString(): String {
        return cardList.joinToString(CARD_STATUS_DELIMITER) {
            "${it.cardNumber.cardNumber.first}${it.cardSign.sign}"
        }
    }

    companion object {

        private const val CARD_STATUS_DELIMITER = ", "
        fun from(cardList: List<Card> = emptyList()) = CardList(cardList)
    }
}