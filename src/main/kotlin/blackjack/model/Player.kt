package blackjack.model

class Player(val name: String, val cards: Cards = Cards.emptyCards(), val stay: Boolean = false) {
    fun scores(): List<Score> {
        return cards.scores
    }

    fun setStay(): Player {
        return Player(name, cards, stay = true)
    }

    fun addCards(newCardList: List<Card>): Player {
        return Player(name, cards.addCards(newCardList), stay)
    }

    fun isGameOver(): Boolean {
        return stay || cards.optimalScore().isBust()
    }

    fun isWinThen(that: Player): Boolean {
        return cards.optimalScore().isWinThan(that.cards.optimalScore())
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        fun createDealer(): Player {
            return Player(DEALER_NAME)
        }
    }
}
