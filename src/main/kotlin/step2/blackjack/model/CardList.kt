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

    /**
     * 카드를 더 받을 수 있는지 체크
     * @param maxValue 최대로 가지고 있을 수 있는 수
     * @return true: 더 받을 수 있음, false: 더 받을 수 없음
     * */
    fun isReceiveCheck(maxValue: Int): Boolean = this.cardList.sumOf { it.cardNumber.cardNumber.second } < maxValue

    operator fun plus(card :Card) {
        require(!cardList.contains(card)) {
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
