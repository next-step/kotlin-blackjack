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
        val cardsString = toCardString(player)
        println("${player.name}카드: $cardsString")
    }

    fun showGameResult(allPlayer: List<Player>) {
        allPlayer.forEach { this.showPlayerCardWithScore(it) }
    }

    private fun showPlayerCardWithScore(player: Player) {
        val cardsString = toCardString(player)
        println("${player.name}카드: $cardsString - 결과: ${player.score}")
    }

    private fun toCardString(player: Player): String {
        return player.myCards()
            .map { CardViewMapper.toView(it) }
            .joinToString { it }
    }
}
