package blackjack.domain

import blackjack.domain.state.State

class Player(val name: String) : Participant() {
    constructor(name: String, state: State) : this(name) {
        this.state = state
    }

    infix fun versus(dealer: Dealer): GameResult {
        val playerScore = this.getScore()
        val dealerScore = dealer.getScore()
        return when {
            playerScore.isBust() && dealerScore.isBust() -> GameResult.LOSE
            dealerScore.isBust() -> GameResult.WIN
            playerScore.isBust() -> GameResult.LOSE
            playerScore.isBlackjack() && dealerScore.isBlackjack() -> GameResult.DRAW
            playerScore.isBlackjack() -> GameResult.WIN
            dealerScore.isBlackjack() -> GameResult.LOSE
            playerScore.value == dealerScore.value -> GameResult.DRAW
            playerScore.gapFromBlackjack() < dealerScore.gapFromBlackjack() -> GameResult.WIN
            playerScore.gapFromBlackjack() == dealerScore.gapFromBlackjack() -> GameResult.DRAW
            playerScore.gapFromBlackjack() > dealerScore.gapFromBlackjack() -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}
