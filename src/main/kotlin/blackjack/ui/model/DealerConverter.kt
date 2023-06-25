package blackjack.ui.model

import blackjack.domain.player.Dealer

object DealerConverter {
    fun convert(dealer: Dealer): DealerOutputModel {
        return DealerOutputModel(dealer.cards)
    }
}
