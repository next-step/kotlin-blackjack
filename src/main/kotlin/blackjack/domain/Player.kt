package blackjack.domain

open class Player(val name: String, val bet: Int) {
    private var hand: Hand = Hand()
    var result: GameResult = GameResult.DRAW
        private set

    fun addCards(newCards: List<Card>) {
        hand = hand.addCards(newCards)
    }

    val cards: List<Card> get() = hand.getCards()
    val score: Int get() = hand.calculateScore()

    open fun canContinue(): Boolean {
        return score <= 21
    }

    fun compareWithDealer(dealer: Dealer): GameResult {
        return when {
            score > 21 -> GameResult.LOSE
            dealer.score > 21 || score > dealer.score -> GameResult.WIN
            score == dealer.score -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    fun setResult(result: GameResult) {
        this.result = result
    }
}
