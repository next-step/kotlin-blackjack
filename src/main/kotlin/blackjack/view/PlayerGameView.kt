package blackjack.view

import blackjack.domain.Player

class PlayerGameView(private val io: IO) {

    fun drawChoice(player: Player): Boolean {
        io.print("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return getDrawChoice()
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
