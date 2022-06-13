package blackjack.view

import blackjack.domain.Player
import blackjack.domain.PlayerInteraction

class PlayerGameView(private val io: IO) : PlayerInteraction {

    override fun getDrawChoice(player: Player): Boolean {
        io.print("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return getDrawChoice()
    }

    override fun printStatus(player: Player) {
        io.print(player.text())
    }

    private tailrec fun getDrawChoice(): Boolean =
        when (io.read().lowercase().trim()) {
            "y" -> true
            "n" -> false
            else -> {
                io.print("잘못된 입력입니다. 다시 입력해주세요.")
                getDrawChoice()
            }
        }
}
