package blackjack.domain.users

import blackjack.domain.result.DealerResult
import blackjack.model.UserCards

class Dealer(
    override val userCards: UserCards,
    var dealerResult: DealerResult = DealerResult()
) : User {
    fun cardReceivePossible(): Boolean {
        return userCards.cardValue() <= 16
    }
}
