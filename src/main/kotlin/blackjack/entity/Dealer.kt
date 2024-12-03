package blackjack.entity

class Dealer : Participant("딜러") {
    fun shouldDrawCard(): Boolean {
        val score = calculateScore()
        return score <= 16
    }

    fun playTurn(deck: Deck): PlayerAction {
        if (shouldDrawCard()) {
            receiveCard(deck.deal())
            return PlayerAction.DRAW
        }
        return PlayerAction.STAND
    }

    fun calculateResult(playerResults: List<GameResult>): GameResult {
        val wins = playerResults.sumOf { it.loses }
        val loses = playerResults.sumOf { it.wins }
        val draws = playerResults.sumOf { it.draws }

        return GameResult(this, wins, loses, draws)
    }

    companion object {
        const val MIN_SCORE_TO_STAND = 17
        const val MAX_SCORE_TO_DRAW = 16
    }
}
