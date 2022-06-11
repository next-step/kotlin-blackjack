package blackjack.domain

open class Participant(
    open val name: String,
    open val playerCards: Cards = Cards(),
    val gameScore: GameScore = GameScore()
) {
    open fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }

    open fun isDealer(): Boolean {
        return this is Dealer
    }
}
