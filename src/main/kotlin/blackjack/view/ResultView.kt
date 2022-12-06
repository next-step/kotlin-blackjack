package blackjack.view

import blackjack.domain.Player

object ResultView {

    fun handingOutCards(players: List<Player>) {
        println()
        val names = players.joinToString { it.name }
        println("${names}에게 2장의 나누었습니다.")
    }

    fun getCurrentStatus(players: List<Player>) {
        players.forEach {
            println("${it.name}카드: ${it.cards.joinToString { card -> "${card.number.text}${card.type.text}" }}")
        }
        println()
    }

    fun getTotalScore(players: List<Player>) {
        players.forEach {
            println("${it.name}카드: ${it.cards.joinToString { card -> "${card.number.text}${card.type.text}" }} - 결과: ${it.score}")
        }
    }
}
