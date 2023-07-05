package step2.domain

data class Player(
    val name: String
) {

    val cards: Cards = Cards()

    fun draw(count: Int) {
        cards.draw(count)
    }

    fun isBurst(): Boolean {
        val currentScore = cards.calculateScore()
        return currentScore.isBurst()
    }
}
