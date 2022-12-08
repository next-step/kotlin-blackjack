package domain

data class Player(val name: PlayerName) {

    val cards: Cards = Cards()

    fun takeCard(card: Card) {
        cards.add(card)
    }

    fun choiceBestScore(): Int = cards.bestScore()

    companion object {
        fun withName(name: String): Player = Player(PlayerName(name))
    }
}
