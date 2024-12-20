package blackjack.domain.player

import blackjack.domain.card.BlackJackCard
import blackjack.domain.state.ResultState

interface Player {
    val name: String
    val cards: MutableList<BlackJackCard>
    val points: Int

    fun getFirstCards(): List<BlackJackCard>

    fun setDoneGame(status: Boolean)

    fun isDoneGame(): Boolean

    fun turn(
        nextCard: () -> BlackJackCard,
        display: (Any) -> Unit,
    )

    fun isBlackJack(): Boolean = // 블랙잭 요건: 21포인트, 카드 2장
        (
            points == BLACKJACK_POINT && cards.size == BLACKJACK_CARD_COUNT
        )

    companion object {
        const val BLACKJACK_POINT = 21
        const val BLACKJACK_CARD_COUNT = 2
        const val ACE_CARD_THRESHOLD = 11
        const val ACE_CARD_EXTRA_POINT = 10
    }
}
