package blackjack.view

object OutputView {

    fun printFirstDeal(users: List<String>) {
        println("${users.joinToString { it }}에게 2장의 카드를 나누었습니다.")
    }
}
