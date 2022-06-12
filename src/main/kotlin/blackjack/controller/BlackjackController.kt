package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.Player
import blackjack.service.InputParser
import blackjack.view.BlackjackView

class BlackjackController {

    fun start() {
        BlackjackView.printPlayerInput()
        val players = getPlayers()
        BlackjackView.printInitialize(players.joinToString(", ") { it.name })

        val blackjack = Blackjack(players)

        players.forEach { blackjack.drawingCards(it, 2) }
        players.forEach { BlackjackView.printPlayerCard(it.name, it.cards.joinToString()) }
        players.forEach { drawCard(blackjack, it) }
        players.forEach { BlackjackView.printResult(it.name, it.cards.joinToString(), blackjack.calculatePoints(it)) }
    }

    private fun getPlayers(): List<Player> {
        return InputParser.parsePlayerName(readLine()!!).map(::Player)
    }

    private fun drawCard(blackjack: Blackjack, player: Player) {
        BlackjackView.printMoreCard(player.name)
        while (InputParser.parseMoreCard(readLine()!!)) {
            BlackjackView.printMoreCard(player.name)
            try {
                blackjack.drawingCard(player)
            } catch (e: IllegalStateException) {
                BlackjackView.printDrawingFailed(e.message.toString())
                break
            }
            BlackjackView.printPlayerCard(player.name, player.cards.joinToString())
        }

        BlackjackView.printPlayerCard(player.name, player.cards.joinToString())
    }
}
