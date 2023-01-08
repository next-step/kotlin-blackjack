package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.Player

typealias QueryAction = (Player) -> String

typealias PrintAction = (Player) -> Unit

class Casino(val gamers: List<Player>) {

    val dealer = Dealer("딜러")
    private val game = Game()

    fun drawTwoCards() {
        repeat(gamers.size) {
            game.drawTwoCards(gamers[it])
        }
        game.drawTwoCards(dealer)
    }

    fun names(): String = gamers.joinToString(", ") { player -> player.name }

    fun relay(queryAction: QueryAction, printAction: PrintAction) {
        var index = 0
        do {
            val player = gamers[index]

            val next = ask(player, queryAction, printAction)

            if (player.canDraw().not()) break

            if (next) index++
        } while (index < gamers.size)

        if (dealer.canDraw()) {
            printAction(dealer)
            game.draw(dealer)
        }
    }

    private fun ask(player: Player, queryAction: QueryAction, printAction: PrintAction): Boolean {
        val skip = question(player, queryAction)
        if (skip) return true

        game.draw(player)

        if (player.canDraw().not()) return true

        printAction(player)

        return ask(player, queryAction, printAction)
    }

    private fun question(player: Player, queryAction: QueryAction): Boolean {
        val answer = queryAction(player)
        if (answer.isBlank()) return true
        if (answer == No) return true

        return false
    }

    companion object {
        private const val No = "n"
    }
}
