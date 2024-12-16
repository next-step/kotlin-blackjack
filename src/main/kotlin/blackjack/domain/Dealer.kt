package blackjack.domain

class Dealer : GameUserInterface {
    private val gameUser = GameUser("딜러")

    override fun getName(): String {
        return gameUser.getName()
    }

    override fun doneGame(status: Boolean) {
        gameUser.doneGame(status)
    }

    override fun isDoneGame(): Boolean {
        return (gameUser.getPoints() > 16) || gameUser.isDoneGame()
    }

    override fun addCard(card: BlackJackCard) {
        gameUser.addCard(card)
    }

    override fun getCards(): List<BlackJackCard> {
        return gameUser.getCards()
    }

    override fun getPoints(): Int {
        return gameUser.getPoints()
    }

    override fun comparePoints(opponent: GameUserInterface): Boolean {
        return gameUser.comparePoints(opponent)
    }
}
