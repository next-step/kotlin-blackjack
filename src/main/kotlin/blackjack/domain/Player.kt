package blackjack.domain

import blackjack.domain.Constants.BLACKJACK_SCORE

class Player(val name: String, val cards: Cards = Cards()) {
    init {
        require(!name.isNullOrBlank()) { "이름은 빈값이 될 수 없습니다." }
    }

    fun hit(card: Card) = cards.add(card)

    fun countingCard(): Int = Score.countingCard(cards)

    fun isBurst(): Boolean = countingCard() > BLACKJACK_SCORE
}
