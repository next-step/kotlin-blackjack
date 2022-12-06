package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import kotlin.math.abs

object BlackJack {

    const val WIN_SCORE: Int = 21

    fun firstPick(players: List<Player>): List<Player> {
        players.forEach { player ->
            repeat(2) {
                player.cards.add(pick())
            }
        }
        return players
    }

    fun pick(): Card {
        val number = CardNumber.getRandomNumber()
        val type = CardType.getRandomCardType()
        return Card(number, type)
    }

    private fun totalScore(player: Player): List<Int> {
        val numbers = player.cards.map { it.number }
        return CardNumber.calculate(numbers)
    }

    fun isOverScore(player: Player): Boolean {
        val totalScore = totalScore(player)
        return totalScore.minOf { it >= WIN_SCORE }
    }

    fun getScore(player: Player): Int {
        val numbers = player.cards.map { it.number }
        val score = CardNumber.calculate(numbers)
        val min = score.minOf { abs(it - WIN_SCORE) }

        return score.first { min == abs(it - WIN_SCORE) }
    }
}
