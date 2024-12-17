package blackjack.view

import blackjack.domain.GameUserInterface

class InputView {
    fun inputUsers(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln()
    }

    fun startGame(
        users: List<GameUserInterface>,
        count: Int,
    ) {
        println("${users.joinToString { it.name }}에게 ${count}장을 나누었습니다.")
        println()
    }

    fun startUser() {
        println()
    }

    fun inputNextDecision(name: String): String {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln()
    }

    fun printAddCardDealer(points: Int) {
        println("딜러는 ${points}이하라 한장의 카드를 더 받았습니다.")
    }
}

class ResultView {
    fun printUserCards(user: GameUserInterface) {
        println("${user.name}카드: ${user.cards}")
    }

    fun printResultCards(
        users: List<GameUserInterface>,
        dealer: GameUserInterface,
    ) {
        println()
        println("${dealer.name}카드: ${dealer.cards} - 결과: ${dealer.points}")
        users.forEach { user ->
            println("${user.name}카드: ${user.cards} - 결과: ${user.points}")
        }

        println("##최종 승패")
        val winCount = users.filter { it.comparePoints(dealer).not() }.size
        println("${dealer.name}카드: ${winCount}승 ${users.size - winCount}패")
        users.forEach { user ->
            println("${user.name}카드: ${getUserResultString(user.comparePoints(dealer))}")
        }
    }

    private fun getUserResultString(resultStatus: Boolean): String {
        return if (resultStatus) "승" else "패"
    }
}
