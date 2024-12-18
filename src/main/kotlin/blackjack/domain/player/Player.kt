package blackjack.domain.player

import blackjack.domain.card.BlackJackCard
import blackjack.view.InputView

interface Player {
    val name: String
    val cards: MutableList<BlackJackCard>
    val points: Int

    fun getFirstCards(): List<BlackJackCard>

    fun setDoneGame(status: Boolean)

    fun isDoneGame(): Boolean

    fun turn(
        inputView: InputView,
        nextCard: () -> BlackJackCard,
    )

    fun comparePoints(opponent: Player): Boolean

    companion object {
        const val BLACKJACK_POINT = 21
        const val ACE_CARD_THRESHOLD = 11
        const val ACE_CARD_EXTRA_POINT = 10
    }
}
