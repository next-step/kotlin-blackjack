package blackjack.model

import blackjack.dto.BlackJackResult
import blackjack.dto.Card
import blackjack.model.Point.Companion.WINNING_POINT

open class Player(val name: String) {

    val cards = mutableListOf<Card>()
    var hit = true
        private set
    var blackJackResult: BlackJackResult? = null
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

    fun compare(player: Player) {
        val point = getPoints()
        val playerPoint = player.getPoints()

        blackJackResult = if (playerPoint in (point + 1)..WINNING_POINT || point > WINNING_POINT) {
            BlackJackResult(point, 0, 1)
        } else {
            BlackJackResult(point, 1, 0)
        }
    }

    fun makeResult(winning: Int, losing: Int) {
        blackJackResult = BlackJackResult(getPoints(), winning, losing)
    }
}
