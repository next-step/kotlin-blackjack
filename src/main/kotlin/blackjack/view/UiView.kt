package blackjack.view

import blackjack.domain.GameUser

class InputView {
    fun inputUsers(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln()
    }

    fun startGame(
        users: List<GameUser>,
        count: Int,
    ) {
        println("${users.joinToString { it.name }}에게 ${count}장을 나누었습니다.")
    }

    fun inputNextDecision(name: String): String {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln()
    }
}

class ResultView {
    fun printUserCards(user: GameUser) {
        println("${user.name}카드: ${user.cards}")
    }

    fun printResultCards(users: List<GameUser>) {
        users.forEach { user ->
            println("${user.name}카드: ${user.cards} - 결과: ${user.points}")
        }
    }
}
