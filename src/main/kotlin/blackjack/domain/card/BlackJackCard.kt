package blackjack.domain.card

class BlackJackCard(private val cardType: CardType, private val cardInfo: CardNumber) {
    fun isAceCard(): Boolean {
        return cardInfo == CardNumber.CARD_A
    }

    fun getPoint(): Int {
        return cardInfo.point
    }

    override fun toString(): String {
        return "${cardInfo.key}${cardType.koreanText}"
    }
}
