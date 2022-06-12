package blackjack.view

import blackjack.domain.GameStatus

class ResultView(private val io: IO) {

    fun run(status: GameStatus) {
        val dealer = status.dealer
        val players = status.players

        io.print("${dealer.text()} - 결과: ${dealer.score.sum}")
        players.forEach {
            io.print("${it.text()} - 결과: ${it.score.sum}")
        }
        io.print("")
    }
}
