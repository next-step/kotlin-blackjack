package blackjack.domain.player

import blackjack.controller.YNBooleanValue
import blackjack.domain.card.BlackJackCard
import blackjack.domain.player.Player.Companion.ACE_CARD_EXTRA_POINT
import blackjack.domain.player.Player.Companion.ACE_CARD_THRESHOLD
import blackjack.domain.player.Player.Companion.BLACKJACK_POINT
import blackjack.view.InputView

class GameUser(override val name: String) : Player {
    private var doneGame = false
    override val cards = mutableListOf<BlackJackCard>()
    override val points: Int
        get() = calculatePoints()

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
        inputView: InputView,
        nextCard: () -> BlackJackCard,
    ) {
        val inputData = inputView.inputNextDecision(this.name)
        val decision = YNBooleanValue.toBoolean(inputData)
        handleCurrentInput(decision, nextCard)
    }

    override fun comparePoints(opponent: Player): Boolean {
        throw IllegalStateException("승패는 딜러와 확인해 주세요")
    }

    private fun handleCurrentInput(
        decision: Boolean?,
        nextCard: () -> BlackJackCard,
    ) {
        // 21이 넘는 경우 종료됨.
        when (decision) {
            true -> cards.add(nextCard())
            false -> setDoneGame(true)
            else -> {}
        }
    }
}
