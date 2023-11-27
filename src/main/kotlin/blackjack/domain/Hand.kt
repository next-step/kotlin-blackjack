package blackjack.domain

class Hand(cardList: List<Card> = emptyList()) {
    private val cardList = cardList.toMutableList()

    fun getCardList(): List<Card> = cardList.toList()

    fun getPossibleScore(): List<Int> {
        val standardScore = cardList.sumOf { it.number.score }

        return (0..countAce()).map {
            standardScore + (it * 10)
        }
    }

    fun add(card: Card) {
        cardList.add(card)
    }

    private fun countAce(): Int =
        cardList.count {
            it.number == CardNumber.ACE
        }
}
