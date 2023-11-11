package blackjack.model

import blackjack.dto.Card

class Player(val name: String) {

    val cardList = mutableListOf<Card>()
    var hit = true
        private set

    init {
        require(name.isNotBlank()) { "이름은 공백일 수 없습니다." }
    }

    fun addCard(card: Card) {
        cardList.add(card)
        if (getPoint() > 21) {
            noMoreHit()
        }
    }

    fun addCards(cards: List<Card>) {
        cardList.addAll(cards)
    }

    fun getPoint() = getPoints().getPoint()

    fun noMoreHit() {
        hit = false
    }

    private fun getPoints() = Point(
        cardList.map { it.number }
    )
}
