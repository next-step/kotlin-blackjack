package blackjack.domain

class Player(
    name: String,
    cards: Cards = Cards(),
) : Participant(name, cards) {
    fun canReceiveCard(): Boolean {
        return !isBust() && !isBlackjack()
    }
}
