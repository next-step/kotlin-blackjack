package blackjack.view

import blackjack.model.Participants

object InputView {
    fun join(): Participants {
        /*
        "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        "${s} 에게 2장의 나누었습니다."
         */
        TODO("Not yet implemented")
    }

    fun draw(participants: Participants): Participants {
        "pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        "pobi카드: 2하트, 8스페이드, A클로버\n"

        TODO()
    }

//    private fun dealerDraw(dealer: Dealer) {
//        "딜러는 16이하라 한장의 카드를 더 받았습니다.\n"
//        TODO()
//    }
}
