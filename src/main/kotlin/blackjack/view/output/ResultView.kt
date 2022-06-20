package blackjack.view.output

import blackjack.player.Player

object ResultView {

    fun showPlayerCard(player: Player) {
        println("${player.name}카드: ${cardView(player)}")
    }

    private fun cardView(player: Player): String {
        return player.show().map { "${it.suit}${it.symbol.value()}" }
            .joinToString { "," }
    }
}
