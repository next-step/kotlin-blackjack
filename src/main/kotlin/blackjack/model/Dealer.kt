package blackjack.model

class Dealer(
    override var cards: Cards = Cards()
) : Gambler {
    override val stopScore = DEALER_STOP_SCORE

    companion object {
        const val DEALER_STOP_SCORE = 16

        fun init(cardDeck: CardDeck, initCardCount: Int): Dealer {
            return Dealer(cardDeck.drawCards(initCardCount))
        }
    }
}
