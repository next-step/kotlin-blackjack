package blackjack.view

import blackjack.domain.GameStatus

class PlayerView(private val io: IO) {

    fun printPlayers(status: GameStatus) {
        val dealer = status.dealer
        val players = status.players

        io.print("${dealer.name}와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        io.print(dealer.textFirstCard())
        players.forEach { io.print(it.text()) }
        io.print("")
    }
}
