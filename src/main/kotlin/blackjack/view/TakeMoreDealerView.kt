package blackjack.view

import blackjack.domain.game.TakeMoreDealer
import blackjack.domain.game.strategy.TakeMoreDealerViewStrategy

class TakeMoreDealerView : TakeMoreDealerViewStrategy {

    override fun printDoneTakeMore() {
        println("딜러는 ${TakeMoreDealer.DEALER_TAKE_ONE_CARD_POLICY}이하라 한장의 카드를 더 받았습니다.")
    }
}
