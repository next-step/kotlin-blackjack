package blackjack.view

import blackjack.domain.Player

class PlayerView(
    private val io: IO,
    private val players: List<Player>,
) {

    fun run() {
        io.print("${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        players.forEach { io.print(it.text()) }
        io.print("")
    }
}
