package blackjack.domain.player

import blackjack.domain.card.BlackJackCard
import blackjack.domain.player.Player.Companion.BLACKJACK_POINT
import blackjack.view.InputView

class Dealer : Player {
    private val gameUser = GameUser(DEALER_NAME)

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
        inputView: InputView,
        nextCard: () -> BlackJackCard,
    ) {
        inputView.printAddCardDealer(points)
        cards.add(nextCard())
    }

    override fun comparePoints(opponent: Player): Boolean {
        return if (gameUser.points > BLACKJACK_POINT) {
            false
        } else {
            (points <= BLACKJACK_POINT) &&
                ((opponent.points > BLACKJACK_POINT) || (points > opponent.points))
        }
    }

    companion object {
        const val DEALER_THRESHOLD_POINTS = 16
        const val DEALER_NAME = "딜러"
    }
}
