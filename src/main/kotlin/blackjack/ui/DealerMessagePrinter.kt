package blackjack.ui

import blackjack.domain.player.Dealer

object DealerMessagePrinter {
    fun shouldHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun hand(dealer: Dealer) = println("딜러카드: ${CardPrinter.message(listOf(dealer.firstOpenCard()))}")

}
