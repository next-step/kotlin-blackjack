package blackjack.domain

import blackjack.domain.BlackJackRules.BLACKJACK_SCORE_LIMIT

data class User(
    val name: String,
    val cards: Cards,
) {
    fun canReceiveCard(): Boolean {
        return cards.calculateScore() < BLACKJACK_SCORE_LIMIT
    }

    fun receiveCard(card: Card): User {
        return this.copy(cards = cards.add(card))
    }

    companion object {
        fun create(name: String): User {
            return User(name, Cards(emptyList()))
        }
    }
}
