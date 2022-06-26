package blackjack

class Score(val cards: List<Card>) {

    fun getScore(): Int {
        return PlayerDeck(cards).calculateScore()
    }
}
