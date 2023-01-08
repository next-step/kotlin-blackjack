package blackjack.controller

import blackjack.NO
import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.Player

typealias QueryAction = (Player) -> String

typealias PrintAction = (Player) -> Unit

class Casino(val gamers: List<Player>) {

    val dealer = Dealer("딜러")
    val game = Game()

    fun init() = game.init(gamers)
    fun distribute() = game.distribute(gamers)

    fun names(): String = gamers.joinToString(", ") { player -> player.name }

    fun printAllPlayers(printAction: PrintAction) {
        printAction(dealer)
        repeat(gamers.size) { index -> printAction(gamers[index]) }
    }

    fun printAllResult(printAction: PrintAction) {
        printAction(dealer)
        repeat(gamers.size) { index -> printAction(gamers[index]) }
    }

    fun relay(queryAction: QueryAction, printAction: PrintAction) {
        var index = 0
        do {
            val player = gamers[index]

            if (player is Dealer && player.canDraw()) printAction(player)

            val next = ask(player, queryAction, printAction)

            if (player.canDraw().not()) break

            if (next) index++
        } while (index < gamers.size)
    }

    private fun ask(player: Player, queryAction: QueryAction, printAction: PrintAction): Boolean {
        val skip = question(player, queryAction)
        if (skip) return true

        draw(player)

        if (player.canDraw().not()) return true

        printAction(player)

        return ask(player, queryAction, printAction)
    }

    private fun question(player: Player, queryAction: QueryAction): Boolean {
        if (player is Dealer) return false

        val answer = queryAction(player)
        if (answer.isBlank()) return true
        if (answer == NO) return true

        return false
    }

    private fun draw(player: Player): Boolean {
        return player.receive(game.draw())
    }
}
