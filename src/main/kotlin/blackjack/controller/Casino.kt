package blackjack.controller

import blackjack.NO
import blackjack.domain.Dealer
import blackjack.domain.Player

typealias QueryAction = (Player) -> String

typealias PrintAction = (Player) -> Unit

class Casino(private val players: List<Player>) {

    private val dealer = Dealer()

    private lateinit var queryAction: QueryAction
    private lateinit var printAction: PrintAction

    fun distribute() = dealer.distribute(players)

    fun names(): String = players.joinToString(", ") { player -> player.name }

    fun printAllPlayers(printAction: PrintAction) = repeat(players.size) { index -> printAction(players[index]) }

    fun printAllResult(printAction: PrintAction) = repeat(players.size) { index -> printAction(players[index]) }

    fun relay(queryAction: QueryAction, printAction: PrintAction) {
        this.queryAction = queryAction
        this.printAction = printAction

        var index = 0
        do {
            val player = players[index]
            val next = ask(player)

            if (player.canDraw().not()) break

            if (next) index++
        } while (index < players.size)
    }

    private fun ask(player: Player): Boolean {
        val answer = queryAction(player)
        if (answer.isBlank()) return true
        if (answer == NO) return true

        draw(player)

        if (player.canDraw().not()) return true

        printAction(player)

        return ask(player)
    }

    private fun draw(player: Player) = player.receive(dealer.draw())
}
