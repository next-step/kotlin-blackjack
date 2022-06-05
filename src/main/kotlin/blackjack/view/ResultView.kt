package blackjack.view

import blackjack.domain.Player

class ResultView(
    private val io: IO,
    private val players: List<Player>,
) {

    fun run() {
        players.forEach {
            io.print("${it.text()} - 결과: ${it.score.sum}")
        }
    }
}
