package blackjack.domain

import blackjack.model.Card

class GameDealer(
    override val play: Play = DealerGamePlay(),
    private val _deck: CardDeck = GameCardDeck()
) : Dealer {
    override val name: String = "딜러"
    override val deck: CardDeck
        get() = _deck

    override fun deliverCard(): Card = _deck.takeOutFirstCard()

    override fun shuffle() = _deck.shuffle()

    companion object {
        private const val STAY_SCORE = 17
    }
}
