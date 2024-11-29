package blackjack

import blackjack.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

enum class Suit {
    SPADE,
    HEART,
    DIAMOND,
    CLUB,
}


data class Rank(val rank: String) {
    init {
        require(rank in ranks) { "랭크는 $rank 이어야 합니다." }
    }

    val score: Int
        get() = rank.toInt()

    companion object {
        private const val MIN_NUMBER = 2
        private const val MAX_NUMBER = 10
        private const val ACE = "A"
        private val NUMBERS = (MIN_NUMBER..MAX_NUMBER).map { it.toString() }
        private val FACES = listOf("J", "Q", "K")
        private val ranks = NUMBERS + FACES + ACE
    }
}

data class Card(val rank: Rank, val suit: Suit)

class BlackjackTest : StringSpec({
    "카드는 랭크와 문양으로 이루어진다" {
        val card = Card(Rank("3"), SPADE)
        card.rank shouldBe Rank("3")
        card.suit shouldBe SPADE
    }

    "카드의 랭크가 2~10,J,Q,K,A가 아닌 경우 예외 발생한다" {
        listOf("0", "1", "-1", "B", "D").forAll { invalidRank ->
            shouldThrow<IllegalArgumentException> { Rank(invalidRank) }
        }
    }

    "랭크가 숫자이면 점수는 숫자의 값으로 계산된다" {
        listOf("2", "3", "4", "5", "6").forAll { number ->
            Rank(number).score shouldBe number.toInt()
        }
    }
})
