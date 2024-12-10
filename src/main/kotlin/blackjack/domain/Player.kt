package blackjack.domain

data class Player(
    val name: String,
) {
    val deck = PlayerCardDeck()

    init {
        require(name.isNotBlank())
    }

    fun addCard(card: Card) {
        deck.addCard(card)
    }

    fun calculateCardScore() {
        deck.calculateScore()
    }

    fun findEnabledMoreCard(isDealer: Boolean = false): Boolean = deck.score.isEnabledMoreCard(isDealer)
}
