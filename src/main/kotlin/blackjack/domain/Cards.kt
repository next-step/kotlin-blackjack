package blackjack.domain

data class Cards(private val _cards: MutableList<Card>) {

    val cards: List<Card>
        get() = _cards.map { it.copy() }

    fun getScore(): Score = Denomination.getScore(this)

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
