package blackjack.domain.participantion

import blackjack.domain.card.Card
import blackjack.domain.card.Card.Companion.BLACK_JACk_NUMBER
import blackjack.domain.card.Cards

sealed class Participant(val name: String, val cards: Cards, val price: Price) {
    var point: Int = 0
        private set

    val priceAmount: Int
        get() = price.amount

    init {
        require(name.isNotBlank()) { "Player 이름은 필수 입력입니다." }
        point = cards.point()
    }

    fun hit(card: Card) {
        cards.add(card)
        point = cards.point()
    }

    fun isWin(participant: Participant): Boolean {
        if (isBust()) return false

        return isBlackJack() || this.point > participant.point
    }

    fun isLose(participant: Participant): Boolean {
        if (participant.isBust()) return false

        return isBust() || this.point < participant.point
    }

    fun losePrice() = price.unaryMinus()

    fun isBust(): Boolean = point > BLACK_JACk_NUMBER

    fun isBlackJack() = cards.size == Cards.INIT_COUNT && point == BLACK_JACk_NUMBER
}
