package blackjack.controller

import blackjack.NO
import blackjack.domain.Dealer
import blackjack.domain.Participant

typealias QueryAction = (Participant) -> String

typealias PrintAction = (Participant) -> Unit

class Casino(participants: List<Participant>) {

    private val dealer = Dealer("딜러")
    private val players: List<Participant> = participants

    fun distribute() = dealer.distribute(players)

    fun names(): String = players.joinToString(", ") { player -> player.name }

    fun printAllPlayers(printAction: PrintAction) {
        printAction(dealer)
        repeat(players.size) { index -> printAction(players[index]) }
    }

    fun printAllResult(printAction: PrintAction) {
        printAction(dealer)
        repeat(players.size) { index -> printAction(players[index]) }
    }

    fun relay(queryAction: QueryAction, printAction: PrintAction) {
        var index = 0
        do {
            val player = players[index]

            if (player is Dealer && player.canDraw()) printAction(player)

            val next = ask(player, queryAction, printAction)

            if (player.canDraw().not()) break

            if (next) index++
        } while (index < players.size)
    }

    private fun ask(player: Participant, queryAction: QueryAction, printAction: PrintAction): Boolean {
        val skip = question(player, queryAction)
        if (skip) return true

        draw(player)

        if (player.canDraw().not()) return true

        printAction(player)

        return ask(player, queryAction, printAction)
    }

    private fun question(player: Participant, queryAction: QueryAction): Boolean {
        if (player is Dealer) return false

        val answer = queryAction(player)
        if (answer.isBlank()) return true
        if (answer == NO) return true

        return false
    }

    private fun draw(player: Participant) = player.receive(dealer.draw())
}
