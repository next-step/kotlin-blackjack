package blackjack.ui.model

import blackjack.domain.player.Dealer

object DealerConverter {
    fun convert(dealer: Dealer): DealerViewModel {
        return DealerViewModel(dealer.cards)
    }
}
