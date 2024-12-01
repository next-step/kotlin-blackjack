package blackjack.domain

class Player(
    name: String,
    cards: Cards = Cards(),
) : Participant(name, cards) {
    override fun canDrawCard(): Boolean {
        return !cards.isOverMaxScore()
    }

    fun isWinner(dealer: Dealer): Boolean {
        if (cards.isBust()) {
            return false
        }

        if (dealer.isBust()) {
            return true
        }

        return cards.calculateScore() > dealer.cards.calculateScore()
    }
}
