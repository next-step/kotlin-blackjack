package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Result
import blackjack.model.Point.Companion.WINNING_POINT

open class Player(val name: String) {

    val cards = mutableListOf<Card>()
    var hit = true
        private set
    var result: Result? = null
        private set
        get() {
            require(field != null) { "게임이 아직 끝나지 않았습니다" }
            return field
        }

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

    fun makeResult(winning: Int, losing: Int) {
        result = Result(getPoints(), winning, losing)
    }

    fun makeWinner() {
        makeResult(1, 0)
    }

    fun makeLoser() {
        makeResult(0, 1)
    }
}
