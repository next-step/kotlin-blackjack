package blackjack.domain

class Player(name: String) : Participant(name) {
    override fun receiveCard(card: Card) {
        state = state.draw(card)
    }

    infix fun versus(dealer: Dealer): GameResult {
        val playerScore = Score(this.cards)
        val dealerScore = Score(dealer.cards)
        return when {
            playerScore.isBust() && dealerScore.isBust() -> GameResult.LOSE
            dealerScore.isBust() -> GameResult.WIN
            playerScore.isBust() -> GameResult.LOSE
            playerScore.isBlackjack() && dealerScore.isBlackjack() -> GameResult.DRAW
            playerScore.isBlackjack() -> GameResult.WIN
            dealerScore.isBlackjack() -> GameResult.LOSE
            dealerScore.calc() == playerScore.calc() -> GameResult.DRAW
            playerScore.gapFromBlackjack() < dealerScore.gapFromBlackjack() -> GameResult.WIN
            playerScore.gapFromBlackjack() > dealerScore.gapFromBlackjack() -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}
