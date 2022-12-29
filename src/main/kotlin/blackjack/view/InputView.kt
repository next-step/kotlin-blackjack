package blackjack.view

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players

object InputView {

    fun enterPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln()
        return Players(
            names.split(",").map { name ->
                Player(
                    PlayerName(name.trim())
                )
            }
        )
    }

    fun chooseDrawCard(player: Player): Boolean {
        println("${player.getName()}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return when (readln()) {
            "Y", "y" -> true
            "N", "n" -> false
            else -> throw IllegalArgumentException("y 또는 n만 입력해야합니다.")
        }
    }
}
