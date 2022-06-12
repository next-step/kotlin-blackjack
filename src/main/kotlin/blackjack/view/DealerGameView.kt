package blackjack.view

import blackjack.domain.Dealer

class DealerGameView(private val io: IO) {

    fun run(drawCount: Int) {
        io.print("")
        repeat(drawCount) {
            io.print("딜러는 ${Dealer.DRAW_THRESHOLD}이하라 한장의 카드를 더 받았습니다.")
        }
        io.print("")
    }
}
