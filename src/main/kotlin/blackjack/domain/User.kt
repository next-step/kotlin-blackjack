package blackjack.domain

data class User(val cards: Cards) {
    fun canReceiveCard(): Boolean {
        return !cards.isFullScore()
    }
}
