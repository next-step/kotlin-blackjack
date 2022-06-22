package blackjack.ui.output

import blackjack.player.BlackjackScoreCalculator
import blackjack.player.Player

object ResultView {

    fun showStartStatus(players: List<Player>) {
        val playerNames = players.map { it.name }
            .joinToString { it }

        println("${playerNames}에게 2장의 카드를 나누었습니다.")
        players.map(this::showPlayerCard)
    }

    fun showPlayerCard(player: Player, score: Int? = null) {
        val cardsString = player.myCards()
            .map { CardViewMapper.toView(it) }
            .joinToString { it }

        val scoreString = score?.let { "- 결과: $it" } ?: ""

        println("${player.name}카드: $cardsString $scoreString")
    }

    fun showGameResult(allPlayer: List<Player>) {
        allPlayer.forEach { this.showPlayerCard(it, BlackjackScoreCalculator.getScore(it.myCards())) }
    }
}
