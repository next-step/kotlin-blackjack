package blackjack.domain

import blackjack.domain.BlackjackUtil.INITIAL_CARD_NUM
import blackjack.domain.BlackjackUtil.isBust

open class Player(val name: String) {
    val hand = Hand()

    init {
        require(name.isNotBlank()) { "플레이어 이름은 빈 값일 수 없습니다." }
    }

    open fun initialCards(): List<Card> {
        return hand.toList().take(INITIAL_CARD_NUM)
    }

    open fun canDrawMore(): Boolean {
        return !isBust(hand.sumOf())
    }
}
