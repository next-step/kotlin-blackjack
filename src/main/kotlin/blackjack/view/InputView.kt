package blackjack.view

import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.ViewUtil.toString

object InputView {
    fun readPlayers(): Players {
        var input: String? = null
        while (input.isNullOrBlank()) {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            input = readLine()
        }

        return Players.Builder().playerNames(input.split(",")).build()
    }

    fun drawCards(player: Player) {
        while (true) {
            println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            if (readLine()?.trim()?.toLowerCase() != "y") {
                break
            }
            player.draw()
            println("${player.name}카드: ${toString(player.cards)}")
        }
    }
}
