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

    private fun play(player: Player) {
        if (!dealer.checkCardDrawable(player)) {
            return
        }
    }
}
