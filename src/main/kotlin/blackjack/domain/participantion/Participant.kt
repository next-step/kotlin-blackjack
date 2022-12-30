package blackjack.domain.participantion

import blackjack.domain.card.Card
import blackjack.domain.card.Card.Companion.BLACK_JACk_NUMBER
import blackjack.domain.card.Cards

sealed class Participant(val name: String, val cards: Cards) {
    var point = 0
        private set

    init {
        require(name.isNotBlank()) { "Player 이름은 필수 입력입니다." }
        point = cards.point()
    }

    fun hit(card: Card) {
        cards.add(card)
        point = cards.point()
    }

    fun isWin(participant: Participant): Boolean = this.point > participant.point

    fun isBust(): Boolean = point > BLACK_JACk_NUMBER
}
