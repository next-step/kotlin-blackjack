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

    fun compareGetResultOpponent(opponent: Player): ResultState {
        // 플레이어가 버스트 되면 딜러가 무조건 이긴다
        if (isBust(opponent.points)) return ResultState.LOSE

        // 21을 넘긴 경우 딜러 버스트가 되어 버스트 되지 않은 모든 플레이어가 승리한다
        val dealerPoints = if (isBust(points)) -1 else points

        // 딜러와 플레이어가 동시에 블랙잭인 경우에는 푸시(Push)라 하여 무승부가 된다. 푸시가 될 경우, 해당 플레이어는 자신이 베팅한 금액을 돌려받는다.
        opponent.points.compareTo(dealerPoints).let {
            return when {
                it > 0 -> if(opponent.isBlackJack()) ResultState.BLACKJACK else ResultState.WIN
                it < 0 -> ResultState.LOSE
                // 같은 경우
                else -> ResultState.PUSH
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
