package blackjack.view.output

import blackjack.domain.player.Dealer

class GameDealerAddCardOutputView(dealer: Dealer) {
    init {
        val isReceivedAddCard = dealer.isReceivable()
        val message = if (isReceivedAddCard) RECEIVED_CARD_MESSAGE else NOT_RECEIVED_CARD_MESSAGE
        println(message + "\n")
    }

    companion object {
        const val RECEIVED_CARD_MESSAGE = "딜러는 ${Dealer.RECEIVABLE_SCORE}이하라 한장의 카드를 더 받았습니다."
        const val NOT_RECEIVED_CARD_MESSAGE = "딜러가 ${Dealer.RECEIVABLE_SCORE}초과라 카드를 더 받지 않았습니다."
    }
}
