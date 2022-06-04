package blackjack

private const val ACE_NUMBER = 1
private const val KING_NUMBER = 13

object Cards {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        for (i in ACE_NUMBER..KING_NUMBER) {
            cards.add(Card(CardShape.SPADE, CardNumber(i)))
            cards.add(Card(CardShape.HEART, CardNumber(i)))
            cards.add(Card(CardShape.DIAMOND, CardNumber(i)))
            cards.add(Card(CardShape.CLOVER, CardNumber(i)))
        }
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }
}
