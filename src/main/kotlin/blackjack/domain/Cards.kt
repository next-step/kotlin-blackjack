package blackjack.domain

class Cards {

    private val cardList: MutableList<Card> = mutableListOf()

    fun getCardList(): List<Card> = cardList.toList()

    fun getTotalScore(isAceEleven: Boolean = false): Int {
        return cardList.sumOf { card ->
            card.getScore(isAceEleven)
        }
    }

    fun add(card: Card) = cardList.add(card)

    fun hasCard(card: Card): Boolean = cardList.contains(card)

    fun hasAce(): Boolean {
        return cardList.any { card -> card.cardNumber == CardNumber.Ace }
    }
}
