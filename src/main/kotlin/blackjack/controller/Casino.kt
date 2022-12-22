package blackjack.controller

import blackjack.NO
import blackjack.domain.Dealer
import blackjack.domain.Participant

typealias QueryAction = (Participant) -> String

typealias PrintAction = (Participant) -> Unit

class Casino(participants: List<Participant>) {

    private val dealer = Dealer("딜러")
    private val players: List<Participant> = participants.toMutableList().apply { add(0, dealer) }

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

    private fun ask(player: Participant): Boolean {
        val answer = queryAction(player)
        if (answer.isBlank()) return true
        if (answer == NO) return true

        draw(player)

        if (player.canDraw().not()) return true

        printAction(player)

        return ask(player)
    }

    private fun draw(player: Participant) = player.receive(dealer.draw())
}
