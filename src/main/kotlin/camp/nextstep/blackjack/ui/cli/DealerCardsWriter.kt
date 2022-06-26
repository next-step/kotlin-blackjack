package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.player.Dealer

object DealerCardsWriter {

    fun write(dealer: Dealer) {
        print("딜러의 카드: ")
        HandWriter.write(dealer.hand)
        println()
    }
}
