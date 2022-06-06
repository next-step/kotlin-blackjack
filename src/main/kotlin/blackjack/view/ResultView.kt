package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player

class ResultView(
    private val io: IO,
    private val dealer: Dealer,
    private val players: List<Player>,
) {

    fun run() {
        io.print(dealer.text())
        players.forEach {
            io.print("${it.text()} - 결과: ${it.score.sum}")
        }
        io.print("")
    }
}
