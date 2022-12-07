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

        println()

        var index = 0
        do {
            val next = relay(gamers[index])
            if (next) index++
        } while (index < gamers.size)

        repeat(gamers.size) {
            val gamer = gamers[it]
            println("$gamer - 결과: ${gamer.getTotalScore()}")
        }
    }

    private fun relay(gamer: Gamer): Boolean {
        println("${gamer.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readLine()
        if (answer.isNullOrBlank()) {
            return true
        }

        if (answer == "n") {
            return true
        }

        gamer.addCard(dealer.getCard())
        println(gamer)

        return relay(gamer)
    }
}
