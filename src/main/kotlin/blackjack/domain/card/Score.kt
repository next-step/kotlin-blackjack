package blackjack.domain.card

import blackjack.domain.card.PlayerDeck

class Score(private val playerDeck: PlayerDeck) {

    fun getScore(): Int {
        return playerDeck.calculateScore()
    }
}
