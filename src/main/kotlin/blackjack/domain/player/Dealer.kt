package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.game.TakeMoreDealer
import blackjack.domain.game.strategy.TakeMoreDealerStrategy

class Dealer(cardDeck: CardDeck) : Player(name = DEALER_NAME, cardDeck) {

    var win: Int = 0
    var lose: Int = 0

    fun play(
        cardDeck: CardDeck,
        takeMoreDealerStrategy: TakeMoreDealerStrategy
    ) {
        val takeMoreDealer = takeMoreDealerStrategy as TakeMoreDealer

        while (canBeTakeOneCard(takeMoreDealer)) {
            takeMoreDealer.printTakeMoreDealer()
            addCard(cardDeck.pickCard())
        }
    }

    private fun canBeTakeOneCard(takeMoreDealerStrategy: TakeMoreDealerStrategy): Boolean {
        return takeMoreDealerStrategy.canBeTakeOneCard(this.score)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
