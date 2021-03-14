package blackjack.ui

import blackjack.domain.Player

object PlayerInputView {
    fun askPlayerNames(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readLine() ?: throw IllegalArgumentException("입력된 이름이 없습니다.")
        println()
        return names.split(",").map { Player(it) }
    }

    fun askMoreCard(player: Player): Boolean {
        println("${player.name}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val agreeYn = readLine() ?: throw IllegalArgumentException("입력된 응답이 없습니다.")
        return when (agreeYn) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("y 또는 n을 입력해주십시오.")
        }
    }
}
