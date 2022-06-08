package blackjack.view

import blackjack.domain.score.PlayerScore

class ResultView {

    fun players(players: List<String>) {
        println(players)
        println("${players.joinToString(", ")} 에게 2장의 나누었습니다.")
    }

    fun score(playerScores: List<PlayerScore>) {
        println()
        playerScores.forEach {
            println("${it.player.name}카드: ${it.player.cards.joinToString(", ")} - 결과: ${it.score}")
        }
    }
}
