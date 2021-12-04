package blackjack.domain

data class Dealer(override val name: Name = Name.from(DEALER_NAME), override val cards: Cards = Cards.from(emptyList())) :
    Player(name, cards) {

    fun makeDealerGameResult(players: Players): GameResult {
        return players.playersExceptedDealer
            .map {
                result(it)
            }
            .groupingBy { it }
            .eachCount()
            .let { GameResult.from(it) }
    }

    override fun canHit(): Boolean {
        return cards.getScore() <= DEALER_ACCEPT_CRITERIA
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

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_ACCEPT_CRITERIA = 16
    }
}
