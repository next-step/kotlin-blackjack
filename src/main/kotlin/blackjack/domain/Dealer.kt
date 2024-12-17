package blackjack.domain

class Dealer : GameUserInterface {
    private val gameUser = GameUser(DEALER_NAME)

    override val name: String
        get() = gameUser.name

    override val cards = gameUser.cards
    override val points: Int
        get() = gameUser.points

    override fun setDoneGame(status: Boolean) {
        gameUser.setDoneGame(status)
    }

    override fun isDoneGame(): Boolean {
        return (gameUser.points > DEALER_THRESHOLD_POINTS) || gameUser.isDoneGame()
    }

    override fun comparePoints(opponent: GameUserInterface): Boolean {
        return gameUser.comparePoints(opponent)
    }

    companion object {
        const val DEALER_THRESHOLD_POINTS = 16
        const val DEALER_NAME = "딜러"
    }
}
