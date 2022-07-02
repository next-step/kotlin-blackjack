package blackjack.view

import blackjack.RequestView

class BlackjackRequestView: RequestView {
    override fun moreCardToDealer() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    override fun moreCardToGamer(name: String) {
        println("%s 님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".format(name))
    }
}
