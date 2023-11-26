package blackjack.domain

import blackjack.domain.BlackjackUtil.DEALER_DRAW_THRESHOLD
import blackjack.domain.BlackjackUtil.DEALER_OPEN_CARD_NUM
import blackjack.domain.BlackjackUtil.computeScore

class Dealer : Player("딜러") {
    private val stack: CardBundle = CardBundle.getBundle()

    fun supplyInitialCards(players: List<Player>) {
        repeat(INITIAL_CARD_NUM) {
            players.forEach { supplyCard(it) }
        }
    }

    fun supplyCard(player: Player) {
        val card = stack.draw() ?: throw IllegalStateException("카드가 부족합니다.")
        player.hand.add(card)
    }

    override fun initialCards(): List<Card> {
        return hand.toList().take(DEALER_OPEN_CARD_NUM)
    }

    override fun canDrawMore(): Boolean {
        // 추가 뽑기 과정에서 딜러의 ACE는 11로 계산한다.
        return computeScore(hand).second <= DEALER_DRAW_THRESHOLD
    }

    companion object {
        const val INITIAL_CARD_NUM = 2
    }
}
