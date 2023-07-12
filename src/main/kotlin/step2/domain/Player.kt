package step2.domain

data class Player(
    val name: String,
) {

    private val currentScore: CurrentScore = CurrentScore()

    private val cards: Cards = Cards()

    fun draw(count: Int) {
        cards.draw(count)
        currentScore.calculateScore(cards.cards)
    }

    fun isBust(): Boolean {
        return currentScore.isBust()
    }

    fun getResultScore(): Int {
        return currentScore.getResultScore()
    }

    fun getJoiningCardNames(delimiter: String): String {
        return cards.cards.joinToString(delimiter) { it.display() }
    }
}
