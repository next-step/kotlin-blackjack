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

    /**
     * @description : 플레이어가 가지고 있는 카드를 반환한다.
     */
    fun getPlayerCards(): String {
        return cards.getCards().joinToString(", ") { it.getCardNumber().toString() + it.getCardType() }
    }

    fun getPlayerCardsSize(): Int {
        return cards.getCards().size
    }

    /**
     * @description : 플레이어가 카드를 한 장 더 뽑는다.
     */
    fun drawCard() {
        cards.addCard(CardDeck.DrawCard().draw())
    }

    /**
     * @description : 플레이어가 가지고 있는 카드의 합을 반환한다.
     */
    fun sumOfCards(): Int {
        return cards.sumCards()
    }

    /**
     * @description : 플레이어가 블랙잭인지 아닌지를 확인한다. 블랙잭이라면 true를 반환한다.
     */
    fun isObtainable(): Boolean {
        return cards.isLessThanBlackJack()
    }

}
