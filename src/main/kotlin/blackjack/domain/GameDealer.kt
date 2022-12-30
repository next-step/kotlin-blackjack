package blackjack.domain

import blackjack.model.Card
import blackjack.model.PlayerProfit

class GameDealer(
    override val play: Play = DealerGamePlay(),
    private val _deck: CardDeck = GameCardDeck()
) : Dealer {
    override val name: String = "딜러"
    override val deck: CardDeck
        get() = _deck

    override fun deliverCard(): Card = _deck.takeOutFirstCard()

    override fun shuffle() = _deck.shuffle()

    override fun profit(gamePlayerProfits: List<PlayerProfit.Player>): PlayerProfit.Dealer =
        gamePlayerProfits
            .sumOf { -it.profit }
            .let {
                PlayerProfit.Dealer(name, it)
            }

    companion object {
        private const val STAY_SCORE = 17
    }
}
