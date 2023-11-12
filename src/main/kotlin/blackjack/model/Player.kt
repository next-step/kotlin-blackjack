package blackjack.model

import blackjack.dto.Card
import blackjack.model.Point.Companion.WINNING_POINT

class Player(val name: String) {

    val cards = mutableListOf<Card>()
    var hit = true
        private set

    init {
        require(name.isNotBlank()) { "이름은 공백일 수 없습니다." }
    }

    fun addCard(card: Card) {
        cards.add(card)
        if (getPoints() > WINNING_POINT) {
            noMoreHit()
        }
    }

    // 처음 두 장의 카드를 받기 위한 함수
    fun addCards(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun getPoints(): Int = toPoint().calculatePoints()

    fun noMoreHit() {
        hit = false
    }

    private fun toPoint(): Point = Point(
        cards.map { it.number }
    )
}
