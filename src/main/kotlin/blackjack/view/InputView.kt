package blackjack.view

import blackjack.domain.player.Player

object InputView {
    fun inputPlayers(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return requireNotNull(readlnOrNull())
    }

    fun inputPlayerHitDecision(player: Player): Boolean {
        println("${player.name}은(는) 한장의 카드를 더 받겠습니까? (y, n)")
        return when (readlnOrNull()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException()
        }
    }
}
