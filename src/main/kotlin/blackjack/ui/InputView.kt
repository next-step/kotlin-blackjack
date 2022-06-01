package blackjack.ui

import blackjack.domain.Behavior

object InputView {

    fun readPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun readBehavior(name: String): Behavior {
        println("${name}은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val rule = readln()
        return if (rule == "y") Behavior.HIT else Behavior.STAY
    }
}
