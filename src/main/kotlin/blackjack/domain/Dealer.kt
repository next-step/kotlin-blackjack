package blackjack.domain

class Dealer(
    override val name: Name = Name.from(DEALER_NAME),
    override val cards: Cards = Cards.from(emptyList())
) : Player(name, cards) {

    fun makeDealerGameResult(playerList: List<Player>): GameResult {
        return playerList
            .map {
                result(it)
            }
            .groupingBy { it }
            .eachCount()
            .let { GameResult.from(it) }
    }

    override fun canHit(): Boolean {
        return cards.getScore() <= Score(DEALER_ACCEPT_CRITERIA_SCORE)
    }

    override fun result(other: Player): GameResultState {
        if (isBust()) return GameResultState.Lose
        if (other.isBust()) return GameResultState.Win
        return when {
            score > other.score -> {
                GameResultState.Win
            }
            score < other.score -> {
                GameResultState.Lose
            }
            else -> {
                GameResultState.Draw
            }
        }
    }

    override fun copy(): Dealer = Dealer(name, cards)

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_ACCEPT_CRITERIA_SCORE = 16
    }
}
