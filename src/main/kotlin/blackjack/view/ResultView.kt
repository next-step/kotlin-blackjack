package blackjack.view

import blackjack.domain.Player

class ResultView {
    fun showInitialPlayers(players: List<Player>, initialCard: Int) {
        println("\n${players.map { it.name }.joinToString()}에게 ${initialCard}장의 카드를 나누었습니다.")
        players.forEach {
            showPlayer(it)
        }
    }

    private fun showPlayer(player: Player) {
        println("${player.name}카드: ${player.getCardList().map { "${it.number.text}${it.shape.text}" }.joinToString()}")
    }
}
