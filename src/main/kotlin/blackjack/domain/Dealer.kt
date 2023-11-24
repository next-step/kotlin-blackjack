package blackjack.domain

import blackjack.domain.BlackjackUtil.DEALER_OPEN_CARD_NUM

class Dealer : Player("딜러") {
    private val stack: CardBundle = CardBundle.getBundle()

    fun supplyCard(player: Player) {
        val card = stack.draw() ?: throw IllegalStateException("카드가 부족합니다.")
        player.hand.add(card)
    }

    override fun initialCards(): List<Card> {
        return hand.toList().take(DEALER_OPEN_CARD_NUM)
    }
}
