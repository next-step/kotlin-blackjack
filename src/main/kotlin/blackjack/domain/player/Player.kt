package blackjack.domain.player

import blackjack.domain.card.BlackJackCard

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

    fun isBlackJack() = (points == BLACKJACK_POINT) && (cards.size == BLACKJACK_CARD_COUNT)

    companion object {
        const val BLACKJACK_POINT = 21
        const val BLACKJACK_CARD_COUNT = 2
        const val ACE_CARD_THRESHOLD = 11
        const val ACE_CARD_EXTRA_POINT = 10
        const val BLACKJACK_EXTRA_REVENUE_RATE = 0.5
    }
}
