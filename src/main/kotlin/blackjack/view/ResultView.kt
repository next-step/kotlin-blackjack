package blackjack.view

import blackjack.domain.Player

object ResultView {

    fun handingOutCards(players: List<Player>) {
        println()
        val names = players.joinToString { it.name }
        println("${names}에게 2장의 나누었습니다.")
    }

    fun getCurrentStatus(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString { card -> "${card.number.text}${card.type.text}" }}")
    }

    fun getTotalScore(player: Player, score: Int) {
        println("${player.name}카드: ${player.cards.joinToString { card -> "${card.number.text}${card.type.text}" }} - 결과: $score")
    }
}
