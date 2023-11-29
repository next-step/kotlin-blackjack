package card

class PlayingCard(private val suit: Suit, private val cardRank: CardRank) {
    fun getPoint(): Int {
        return cardRank.point
    }

    fun getSuitName(): String {
        return suit.koName
    }

    fun getCardNumber(): CardRank {
        return cardRank
    }

    override fun toString(): String {
        return "${cardRank.symbol}${suit.koName}"
    }
}
