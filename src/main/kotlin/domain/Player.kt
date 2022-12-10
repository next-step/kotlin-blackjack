package domain

class Player(val name: PlayerName) {

    val cards: Cards = Cards()

    fun takeCards(vararg cards: Card) {
        cards.forEach { this.cards.add(it) }
    }

    fun choiceBestScore(): Int = cards.bestScore()

    fun canDrawCard() = choiceBestScore() < Score.MAX_SCORE

    companion object {
        fun withName(name: String): Player = Player(PlayerName(name))
    }
}
