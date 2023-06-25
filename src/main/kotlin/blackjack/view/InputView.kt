package blackjack.view

import blackjack.domain.Player

private const val NAME_STRING = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

object InputView {
    fun getNames(): List<Player> {
        println(NAME_STRING)
        return readln().split(",").map { Player(it) }
    }
}