package blackjack.domain

class Player(
    val name: String,
    val price: Int
) : Participant {
    private val cards = mutableSetOf<Card>()
    val cardNames: List<String>
        get() = cards.map { it.toString() }
    val cardSize: Int
        get() = cards.size
    override val isBlackjack: Boolean
        get() = _isBlackjack
    private var _isBlackjack: Boolean = false

    constructor(name: String, cards: Set<Card>) : this(name) {
        this.cards.addAll(cards)
    }

    constructor(name: String) : this(name, 0)

    override fun takeCard(card: Card) {

        check(cardPointSum() <= BLACK_JACK_TWENTY_ONE) { "21점이 넘어서 더 이상 카드를 받을 수 없습니다." }

        cards.add(card)
    }

    override fun takeFirstTwoCards(card1: Card, card2: Card) {
        takeCard(card1)
        takeCard(card2)

        if (cardPointSum() == BLACK_JACK_TWENTY_ONE) {
            _isBlackjack = true
        }
    }

    override fun cardPointSum(): Int {
        var cardPointSum = cards.sumBy { it.point }
        val aceCount = cards.count { it.isAce }

        repeat(aceCount) {
            cardPointSum = changeAcePointToOneToWin(cardPointSum)
        }
        return cardPointSum
    }

    private fun changeAcePointToOneToWin(cardPointSum: Int): Int =
        if (cardPointSum > BLACK_JACK_TWENTY_ONE) cardPointSum - CardType.DECREMENTABLE_POINT_OF_ACE else cardPointSum

    companion object {
        const val BLACK_JACK_TWENTY_ONE = 21
    }
}
