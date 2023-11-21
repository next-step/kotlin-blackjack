package blackjack.view

import blackjack.domain.PreparedPlayer

object InputView {
    fun getPlayers(): List<PreparedPlayer> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return readln().split(",")
            .map { PreparedPlayer(it) }
    }

    fun isHit(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> error("y/n 으로만 입력해주세요")
        }
    }
}
