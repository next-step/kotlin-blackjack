package camp.nextstep.edu.step.step2.domain.player

import camp.nextstep.edu.step.step2.domain.card.CardDeck
import camp.nextstep.edu.step.step2.domain.card.Cards

class Player(
    val name: String,
    val cards: Cards
) {
    init {
        require(name.isNotBlank()) { "플레이어의 이름은 빈 값이 될 수 없습니다." }
    }

    fun getPlayerCards(): String {
        return cards.getCards().joinToString(", ") { it.getCardNumber().toString() + it.getCardType() }
    }

    fun getPlayerCardsSize(): Int {
        return cards.getCards().size
    }

    fun drawCard() {
        cards.addCard(CardDeck.DrawCard().draw())
    }

    fun sumOfCards(): Int {
        return cards.sumCards()
    }

    fun isObtainable(): Boolean {
        return cards.isLessThanBlackJack()
    }

}
