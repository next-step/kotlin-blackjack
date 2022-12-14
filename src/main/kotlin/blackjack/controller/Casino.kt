package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Player

typealias Question = (Player) -> Boolean

typealias Print = (Player) -> Unit

class Casino(private val players: List<Player>) {

    private val dealer = Dealer()

    private lateinit var question: Question
    private lateinit var print: Print

    fun distribute() = dealer.distribute(players)

    fun names(): String = players.joinToString(", ") { player -> player.name }

    fun printAllPlayers(print: Print) = repeat(players.size) { index -> print(players[index]) }

    fun printAllResult(print: Print) = repeat(players.size) { index -> print(players[index]) }

    fun relay(questionAction: Question, printAction: Print) {
        question = questionAction
        print = printAction
        var index = 0
        do {
            val player = players[index]
            val next = ask(player)

            if (player.canDraw().not()) break

            if (next) index++
        } while (index < players.size)
    }

    private fun ask(player: Player): Boolean {
        val result = question(player)
        if (result) return true

        draw(player)

        if (player.canDraw().not()) return true

        print(player)

        return ask(player)
    }

    private fun draw(player: Player) = player.receive(dealer.draw())

}
