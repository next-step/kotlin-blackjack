package blackjack.domain

import blackjack.domain.Const.BLACKJACK_NUMBER

class Player(
    val name: String,
    private val cards: Cards
) {
    val cardElements: Set<Card>
        get() = cards.elements

    fun addCard(card: Card) {
        this.cards.add(card)
    }

    fun resultScore() = this.cards.score()
    fun ableMoreDrawCard() = resultScore() < BLACKJACK_NUMBER

    init {
        require(name.isNotBlank()) { "이름은 빈 값이 올 수 없어요" }
    }
}
