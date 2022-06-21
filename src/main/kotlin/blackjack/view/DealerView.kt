package blackjack.view

import blackjack.domain.Dealer

object DealerView {

    private const val HIDDEN_CARD = "??"

    fun printMoreCard(dealer: Dealer) {
        println("${dealer.name}는 ${Dealer.STOP_POINT}점 미만이라 한 장의 카드를 더 받았습니다.")
    }

    fun printFirstCard(dealer: Dealer) {
        println("${dealer.name}: ${dealer.cards.first()}, $HIDDEN_CARD")
    }
}
