package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player

class PlayerView(
    private val io: IO,
    private val dealer: Dealer,
    private val players: List<Player>,
) {

    fun run() {
        io.print("${dealer.name}와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        io.print(dealer.textFirstCard())
        players.forEach { io.print(it.text()) }
        io.print("")
    }
}
