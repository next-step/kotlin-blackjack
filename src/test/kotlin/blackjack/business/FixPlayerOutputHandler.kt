package blackjack.business

import blackjack.view.PlayerOutputHandler

class FixPlayerOutputHandler : PlayerOutputHandler {
    val printedPlayer: MutableList<Player> = mutableListOf()

    override fun print(player: Player) {
        printedPlayer.add(player)
    }
}
