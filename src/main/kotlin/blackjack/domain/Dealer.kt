package blackjack.domain

class Dealer(
    override val name: String,
    override val playerCards: Cards = Cards()
) : Participant(name, playerCards) {

    override fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }

    override fun isDealer(): Boolean {
        return true
    }
}
