package blackjack.view

import blackjack.domain.Player

class ResultView {
    fun showInitialPlayers(players: List<Player>, initialCard: Int) {
        println("\n${players.map { it.name }.joinToString()}에게 ${initialCard}장의 카드를 나누었습니다.")
        players.forEach {
            showPlayer(it)
        }
    }

    fun showPlayer(player: Player) {
        println(playerText(player = player))
    }

    fun showResult(players: List<Player>) {
        println(
            players.joinToString("\n") {
                "${playerText(player = it)} - ${playerScore(player = it)}"
            }
        )
    }

    private fun playerText(player: Player): String =
        "${player.name}카드: ${player.getCardList().joinToString { "${it.number.text}${it.shape.text}" }}"

    private fun playerScore(player: Player): String = "결과: ${player.getScore()}"
}
