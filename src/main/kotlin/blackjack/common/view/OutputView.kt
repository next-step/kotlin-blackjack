package blackjack.common.view

object OutputView {

    fun beginNameNotice(playerNames: String) {
        println("\n딜러와 ${playerNames}에게 2장의 나누었습니다.")
    }

    fun dealerAddNotice() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun canNotDrawMoreWarn(name: String) {
        println("${ name}는 핸드 총합이 21이상을 넘어서 더이상 받을 수 없습니다.")
    }
}
