package blackjack.domain

import blackjack.domain.BlackjackUtil.computeScore

class Dealer : Player("딜러") {
    private val stack: CardBundle = CardBundle.getBundle()

    fun supplyInitialCards(players: List<Player>) {
        repeat(INITIAL_CARD_NUM) {
            players.forEach { supplyCard(it) }
        }
    }

    fun supplyCard(player: Player) {
        player.hand.add(stack.draw())
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
        const val DEALER_OPEN_CARD_NUM = 1
        const val DEALER_DRAW_THRESHOLD = 16
    }
}
