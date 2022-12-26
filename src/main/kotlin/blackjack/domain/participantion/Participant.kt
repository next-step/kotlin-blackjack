package blackjack.domain.participantion

import blackjack.domain.card.Card
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

    fun isBust(): Boolean = point > Cards.BLACK_JACk_NUMBER
}
