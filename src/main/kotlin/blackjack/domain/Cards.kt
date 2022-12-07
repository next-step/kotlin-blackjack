package blackjack.domain

class Cards {

    private var cardList: MutableList<Card> = mutableListOf()

    fun getTotalScore(isAceEleven: Boolean = false): Int {
        return cardList.sumOf { card ->
            card.toInt(isAceEleven)
        }
    }

    fun addCard(card: Card) = cardList.add(card)

    fun hasCard(card: Card): Boolean = cardList.contains(card)
}
