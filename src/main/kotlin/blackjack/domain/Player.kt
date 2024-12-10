package blackjack.domain

import blackjack.BlackJackGame.Companion.BLACK_JACK_NUMBER

data class Player(
    val name: String,
) {
    val deck = PlayerCardDeck()

    fun addCard(card: Card) {
        deck.addCard(card)
    }

    fun calculateCardScore() {
        deck.calculateScore()
    }

    fun findEnabledMoreCard(): Boolean = deck.score <= BLACK_JACK_NUMBER
}
