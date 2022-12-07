package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Gamer

class ResultView {

    private val dealer = Dealer()

    fun show(gamers: List<Gamer>) {
        introduceGamers(gamers)
        distribute(gamers)
        println()
        relay(gamers)
        showResult(gamers)
    }

    private fun showResult(gamers: List<Gamer>) {
        repeat(gamers.size) {
            val gamer = gamers[it]
            println("$gamer - 결과: ${gamer.getTotalScore()}")
        }
    }

    private fun ask(gamer: Gamer): Boolean {
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

        return ask(gamer)
    }

    private fun relay(gamers: List<Gamer>) {
        var index = 0
        do {
            val next = ask(gamers[index])
            if (next) index++
        } while (index < gamers.size)
    }

    private fun distribute(gamers: List<Gamer>) {
        repeat(gamers.size) {
            val gamer = gamers[it]
            repeat(2) { gamer.addCard(dealer.getCard()) }
            println(gamer)
        }
    }

    private fun introduceGamers(gamers: List<Gamer>) {
        val guide = gamers.joinToString(", ") { it.name }
        println("${guide}에게 2장의 나누었습니다.")
    }
}
