package blackjack.view

import blackjack.business.participants.Dealer

object DetailedDealerOutputHandler {
    fun print(dealer: Dealer) =
        println("딜러 카드: ${dealer.cards.joinToString(", ")} - 결과: ${dealer.score}")
}
