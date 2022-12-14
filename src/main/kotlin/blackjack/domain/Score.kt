package blackjack.domain

import blackjack.domain.card.CardNumber
import kotlin.math.abs

object Score {

    private fun calculate(numbers: List<CardNumber>): List<Int> {
        val sum = numbers.sumOf { it.value.first() }
        val countOfAce = numbers.count { it == CardNumber.ACE }

        val result = mutableListOf(sum)
        repeat(countOfAce) {
            result.add(result[it] + CardNumber.ACE.value[1] - CardNumber.ACE.value[0])
        }
        return result
    }

    private fun totalScore(player: Player): List<Int> {
        val numbers = player.cards.list.map { it.number }
        return calculate(numbers)
    }

    fun isBust(player: Player): Boolean {
        val totalScore = totalScore(player)
        return totalScore.minOf { it >= BlackJack.WIN_SCORE }
    }

    fun getFinalScore(player: Player): Int {
        val numbers = player.cards.list.map { it.number }
        val scoreList = calculate(numbers)
        val min = scoreList.minOf { abs(it - BlackJack.WIN_SCORE) }

        return scoreList.first { min == abs(it - BlackJack.WIN_SCORE) }
    }
}
