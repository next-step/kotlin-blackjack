package blackjack.domain

class Player(val name: String, cardList: List<Card> = emptyList()) {
    private val cardList: MutableList<Card> = cardList.toMutableList()

    fun getCardList(): List<Card> {
        return cardList.toList()
    }
}
