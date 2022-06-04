package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player

class GameView(
    private val io: IO,
    private val dealer: Dealer,
    private val players: List<Player>,
) {

    fun run() {
        players.forEach(::play)
        io.print("")
    }

    private tailrec fun play(player: Player) {
        if (!dealer.checkCardDrawable(player)) {
            return
        }

        io.print("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val shouldGiveCard = getDrawChoice()
        if (shouldGiveCard) {
            dealer.giveCard(player)
            io.print(player.text())
            play(player)
        }
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
