package blackjack.common.view

object OutputView {

    fun beginNameNotice(playerNames: String) {
        println("\n딜러와 ${playerNames}에게 2장의 나누었습니다.")
    }

    fun dealerAddNotice() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
