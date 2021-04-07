package blackjack.domain

class Player(val name: String, val playerCards: Cards) {

    fun isMoreCard(): Boolean {
        return playerCards.calculateScore() < Cards.BLACKJACK_WINNER_POINT
    }
}
