package blackjack.domain

data class User(private val cards: Cards) {
    fun canReceiveCard(): Boolean {
        return !cards.isFullScore()
    }

    fun receiveCard(card: Card): User {
        return this.copy(cards = cards.add(card))
    }
}
