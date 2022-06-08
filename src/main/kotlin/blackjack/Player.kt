package blackjack

class Player(val name: String, val cards: Cards, val gameOver: Boolean = false) {
    fun scores(): List<Score> {
        return cards.scores
    }

    fun gameOver(): Player {
        return Player(name, cards, gameOver = true)
    }

    fun addCards(newCardList: List<Card>): Player {
        return Player(name, cards.addCards(newCardList), gameOver)
    }
}
