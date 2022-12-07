package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Gamer

class ResultView {

    private val dealer = Dealer()

    fun show(gamers: List<Gamer>) {
        val guide = gamers.joinToString(", ") {
            it.name
        }

        println("${guide}에게 2장의 나누었습니다.")

        repeat(gamers.size) {
            val gamer = gamers[it].apply {
                addCard(dealer.getCard())
                addCard(dealer.getCard())
            }
            println(gamer)
        }
    }
}
