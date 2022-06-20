package blackjack.ui.output

import blackjack.player.Player

object ResultView {

    fun showStartStatus(players: List<Player>) {
        val playerNames = players.map { it.name }
            .joinToString { it }

        println("${playerNames}에게 2장의 카드를 나누었습니다.")
        players.map(this::showPlayerCard)
    }

    fun showPlayerCard(player: Player) {
        val cards = player.show().map { "${it.symbol.symbol}${it.suit.value}" }
            .joinToString { it }

        println("${player.name}카드: $cards")
    }
}
