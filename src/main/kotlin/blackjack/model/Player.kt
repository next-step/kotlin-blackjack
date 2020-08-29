package blackjack.model

const val WIN_SCORE = 21

open class Player(val name: String, private val cards: Cards = Cards(emptyList())) {
    var gameResult: GameResult = GameResult.DRAW
        private set

    fun draw(vararg cards: Card) {
        repeat(cards.size) {
            this.cards.add(cards[it])
        }
    }

    fun score(): Int {
        return cards.score()
    }

    fun available(): Boolean {
        return score() < WIN_SCORE
    }

    fun cardToString(): String {
        return cards.toString()
    }

    fun gameResult(dealerPoint: Int) {
        when {
            isOverWinScore() -> this.gameResult = GameResult.LOSE
            dealerPoint > WIN_SCORE -> this.gameResult = GameResult.WIN
            this.score() > dealerPoint -> this.gameResult = GameResult.WIN
            this.score() == dealerPoint -> this.gameResult = GameResult.DRAW
            this.score() < dealerPoint -> this.gameResult = GameResult.LOSE
        }
    }

    private fun isOverWinScore(): Boolean {
        return score() > WIN_SCORE
    }
}
