package blackjack.view

import blackjack.domain.player.Player

object InputView {
    fun inputNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names =
            readlnOrNull()?.split(",")
                ?.map { it.trim() }
                ?: throw IllegalArgumentException("잘못된 입력입니다")
        return names
    }

    fun inputHit(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readlnOrNull()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("잘못된 입력입니다")
        }
    }
}
