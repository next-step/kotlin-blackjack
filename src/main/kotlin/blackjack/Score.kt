package blackjack

class Score(private val playerDeck: PlayerDeck) {

    fun getScore(): Int {
        return playerDeck.calculateScore()
    }
}
