package blackjack.domain.player

import blackjack.domain.card.BlackJackCard
import blackjack.domain.player.Player.Companion.ACE_CARD_EXTRA_POINT
import blackjack.domain.player.Player.Companion.ACE_CARD_THRESHOLD
import blackjack.domain.player.Player.Companion.BLACKJACK_EXTRA_REVENUE_RATE
import blackjack.domain.player.Player.Companion.BLACKJACK_POINT
import blackjack.domain.state.InputState
import blackjack.domain.state.ResultState

class GameUser(
    override val name: String,
    val bettingMoney: Int = 0,
    private val decision: () -> InputState = {
        InputState.STAY
    },
) : Player {
    var resultState = ResultState.WIN
    private var doneGame = false
    override val cards = mutableListOf<BlackJackCard>()
    override val points: Int
        get() = calculatePoints()

    fun bettingRevenue(): Int {
        val revenueRate = resultState.revenueRate + if (isBlackJack()) BLACKJACK_EXTRA_REVENUE_RATE else 0.0
        return (revenueRate * bettingMoney).toInt()
    }

    override fun getFirstCards(): List<BlackJackCard> {
        return cards
    }

    override fun setDoneGame(status: Boolean) {
        doneGame = status
    }

    override fun isDoneGame(): Boolean {
        return doneGame || calculatePoints() >= BLACKJACK_POINT
    }

    private fun calculatePoints(): Int {
        val sumPoint =
            cards.sumOf {
                it.getPoint()
            }
        val aceCount = cards.any { it.isAceCard() }
        return consumeAceCard(sumPoint, aceCount)
    }

    private fun consumeAceCard(
        sumPoint: Int,
        hasAceCard: Boolean,
    ): Int {
        return sumPoint +
            if (hasAceCard && sumPoint <= ACE_CARD_THRESHOLD) {
                ACE_CARD_EXTRA_POINT
            } else {
                0
            }
    }

    override fun turn(
        nextCard: () -> BlackJackCard,
        display: (Any) -> Unit,
    ) {
        // 21이 넘는 경우 종료됨.
        when (decision()) {
            InputState.HIT -> {
                cards.add(nextCard())
                display(cards)
            }
            InputState.STAY -> setDoneGame(true)
            InputState.NONE -> {}
        }
    }
}
