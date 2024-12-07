package blackjack.entity

class Dealer : Participant("딜러") {
    fun initializeHand(deck: Deck) {
        repeat(2) { receiveCard(deck.deal()) }
    }

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

    fun calculateResult(players: Players): GameResult {
        val playerEarned = players.calculatePlayerEarned(this)

        return GameResult(this, -playerEarned)
    }

    companion object {
        const val MIN_SCORE_TO_STAND = 17
        const val MAX_SCORE_TO_DRAW = 16
    }
}
