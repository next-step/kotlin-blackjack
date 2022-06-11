package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.game.TakeMoreDealerStrategy

class Dealer : Player(_name = DEALER_NAME) {

    var win: Int = 0
    var lose: Int = 0

    fun play(cardDeck: CardDeck, takeMoreDealerStrategy: TakeMoreDealerStrategy) {
        while (canBeTakeOneCard(takeMoreDealerStrategy)) {
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
