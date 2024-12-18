package blackjack.domain.player

import blackjack.domain.card.BlackJackCard
import blackjack.domain.player.Player.Companion.BLACKJACK_POINT
import blackjack.domain.state.InputState
import blackjack.domain.state.ResultState

class Dealer : Player {
    private val gameUser =
        GameUser(DEALER_NAME) {
            if (points > DEALER_THRESHOLD_POINTS) {
                InputState.STAY
            } else {
                InputState.HIT
            }
        }

    override val name: String
        get() = gameUser.name

    override val cards = gameUser.cards
    override val points: Int
        get() = gameUser.points

    override fun getFirstCards(): List<BlackJackCard> {
        check(cards.size > 0) { "카드가 없어요" }
        return cards.take(1)
    }

    override fun setDoneGame(status: Boolean) {
        gameUser.setDoneGame(status)
    }

    override fun isDoneGame(): Boolean {
        return (gameUser.points > DEALER_THRESHOLD_POINTS) || gameUser.isDoneGame()
    }

    override fun turn(
        nextCard: () -> BlackJackCard,
        display: (Any) -> Unit,
    ) {
        display("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        cards.add(nextCard())
    }

    override fun comparePoints(opponent: Player): ResultState {
        if (isBust(points)) return ResultState.LOSE
        if (isBust(opponent.points)) return ResultState.WIN
        points.compareTo(opponent.points).let {
            when {
                it > 0 -> return ResultState.WIN
                it < 0 -> return ResultState.LOSE
                else -> return ResultState.DRAW
            }
        }
    }

    private fun isBust(points: Int): Boolean {
        return points > BLACKJACK_POINT
    }

    companion object {
        const val DEALER_THRESHOLD_POINTS = 16
        const val DEALER_NAME = "딜러"
    }
}
