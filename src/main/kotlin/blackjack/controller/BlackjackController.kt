package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.Player
import blackjack.service.InputParser
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackView

class BlackjackController {

    fun start() {
        BlackjackView.printPlayerInput()
        val players = getPlayers()
        BlackjackView.printInitialize(players)

        val blackjack = Blackjack(players)

        blackjack.drawFirstCards()
        players.forEach { BlackjackView.printPlayerCard(it.name, it.cards) }
        players.forEach { drawCard(blackjack, it) }
        players.forEach { BlackjackView.printResult(it.name, it.cards, it.getPoints()) }
    }

    private fun getPlayers(): List<Player> {
        val names = BlackjackInputView.readPlayerNames()

        return InputParser.parsePlayerName(names).map(::Player)
    }

    private fun drawCard(blackjack: Blackjack, player: Player) {
        if (!blackjack.isDrawable(player)) return BlackjackView.printCanNotDrawCard(player)

        BlackjackView.printMoreCard(player.name)
        while (InputParser.parseMoreCard(BlackjackInputView.readMoreCard())) {
            BlackjackView.printMoreCard(player.name)
            blackjack.drawCard(player)
            BlackjackView.printPlayerCard(player.name, player.cards)

            if (!blackjack.isDrawable(player)) return BlackjackView.printCanNotDrawCard(player)
        }

        BlackjackView.printPlayerCard(player.name, player.cards)
    }
}
