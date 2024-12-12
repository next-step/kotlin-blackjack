package blackjack.domain

class BlackJackCard(value: Int) {
    private val cardInfo: CardInfo
    private val cardType: CardType

    init {
        this.cardType = CardType.entries[value.minus(1) / 13]
        this.cardInfo = CardInfo.entries[value.minus(1) % 13]
    }

    enum class CardType(val koreanText: String) {
        SPADE("스페이드"),
        CLOVER("클로버"),
        DIAMOND("다이아"),
        HEART("하트"),
    }

    enum class CardInfo(val key: String, val point: Int) {
        CARD_A("A", 1),
        CARD_2("2", 2),
        CARD_3("3", 3),
        CARD_4("4", 4),
        CARD_5("5", 5),
        CARD_6("6", 6),
        CARD_7("7", 7),
        CARD_8("8", 8),
        CARD_9("9", 9),
        CARD_10("10", 10),
        CARD_J("J", 10),
        CARD_Q("Q", 10),
        CARD_K("K", 10),
    }

    fun isAceCard(): Boolean {
        return cardInfo == CardInfo.CARD_A
    }

    fun getPoint(): Int {
        return cardInfo.point
    }

    override fun toString(): String {
        return "${cardInfo.key}${cardType.koreanText}"
    }
}
