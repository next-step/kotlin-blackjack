package blackjack.domain

@JvmInline
value class Cards private constructor(private val _cards: MutableList<Card>) {

    val cards: List<Card>
        get() = _cards.map { it.copy() }

    fun getScore(): Score = NumberType.getScore(this)

    fun countAce(): Int {
        return _cards.count(Card::hasAce)
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    companion object {
        fun from(cardList: List<Card> = listOf()): Cards {
            return Cards(cardList.toMutableList())
        }
    }
}
