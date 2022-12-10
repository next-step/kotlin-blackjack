package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Player

class ResultView {

    private val dealer = Dealer()

    fun show(players: List<Player>) {
        introduceGamers(players)
        distribute(players)
        println()
        relay(players)
        showResult(players)
    }

    private fun introduceGamers(players: List<Player>) {
        val guide = players.joinToString(", ") { it.name }
        println("${guide}에게 2장의 나누었습니다.")
    }

    private fun distribute(players: List<Player>) {
        repeat(players.size) {
            val player = players[it]
            repeat(2) { player.receive(dealer.draw()) }
            println(player)
        }
    }

    private fun relay(players: List<Player>) {
        var index = 0
        do {
            val player = players[index]
            val next = ask(player)

            if (player.canDraw().not()) break

            if (next) index++
        } while (index < players.size)
    }

    private fun ask(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readLine()
        if (answer.isNullOrBlank()) {
            return true
        }

        if (answer == "n") {
            return true
        }

        player.receive(dealer.draw())

        if (player.canDraw().not()) return true

        println(player)

        return ask(player)
    }

    private fun showResult(players: List<Player>) {
        repeat(players.size) {
            val gamer = players[it]
            println("$gamer - 결과: ${gamer.totalScore}")
        }
    }
}
