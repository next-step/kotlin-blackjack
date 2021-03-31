package blackjack.model.trump

import blackjack.model.score.Scores

data class Card internal constructor(val cardNumber: CardNumber, val suit: Suit) {
    fun isAce(): Boolean {
        return cardNumber == CardNumber.ACE
    }

    fun getScores(): Scores {
        return cardNumber.scores
    }
}
