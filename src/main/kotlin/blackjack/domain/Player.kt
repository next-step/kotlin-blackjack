package blackjack.domain

class Player(val name: String, cardList: List<Card> = emptyList(), private var isEnded: Boolean = false) {
    private val cardList: MutableList<Card> = cardList.toMutableList()

    fun getCardList(): List<Card> {
        return cardList.toList()
    }

    fun canDraw(): Boolean =
        cardList.sumOf { it.number.score } <= BlackjackRule.targetScore && !isEnded

    fun draw(deck: Deck) {
        cardList.add(deck.pop())
    }

    fun endTurn() {
        isEnded = true
    }
}
