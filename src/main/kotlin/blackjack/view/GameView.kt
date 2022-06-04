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

        val shouldGiveCard = getDrawChoice(player.name)
        if (shouldGiveCard) {
            dealer.giveCard(player)
            io.print(player.text())
            play(player)
        }
    }

    private fun getDrawChoice(name: String): Boolean {
        io.print("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return io.read().lowercase().trim() == "y"
    }
}
