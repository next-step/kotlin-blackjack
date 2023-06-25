package blackjack.view

import blackjack.domain.player.Player

object InputView {
    const val YES = "y"
    const val NO = "n"
    fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun askDrawCard(player: Player): Boolean {
        println("${player.name}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            YES -> true
            NO -> false
            else -> throw IllegalArgumentException("잘못된 입력입니다.")
        }
    }
}
