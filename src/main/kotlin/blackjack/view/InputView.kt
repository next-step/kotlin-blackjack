package blackjack.view

import blackjack.domain.player.Player

object InputView {
    fun players(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln()
        return try {
            Player.ofList(names).also { println() }
        } catch (e: IllegalArgumentException) {
            println("쉼표 기준으로 참가자를 입력해주세요. 입력:($names)")
            players()
        }
    }

    fun isHit(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> {
                println("예는 y, 아니오는 n으로 입력해주세요.")
                isHit(player)
            }
        }
    }
}
