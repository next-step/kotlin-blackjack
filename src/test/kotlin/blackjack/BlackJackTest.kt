package blackjack

import blackjack.Rank.Companion.ACE
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

@JvmInline
value class Rank(private val value: String) {
    init {
        require(value in RANK_VALUES) { "랭크는 $RANK_VALUES 에 포함되어야 합니다." }
    }

    val score: Int
        get() =
            when (value) {
                in NUMBER_VALUES -> value.toInt()
                in FACE_VALUES -> 10
                else -> throw IllegalArgumentException("에이스는 경우에 따라 점수를 합산해야 합니다")
            }

    companion object {
        private const val ACE_VALUE = "A"

        private val NUMBER_VALUES = listOf("2", "3", "4", "5", "6", "7", "8", "9")
        private val FACE_VALUES = listOf("J", "Q", "K")
        private val RANK_VALUES = NUMBER_VALUES + FACE_VALUES + ACE_VALUE

        val ACE = Rank(ACE_VALUE)
    }
}

data class Card(val rank: Rank, val suit: Suit)

data class Cards(val cards: List<Card>) {
    fun calculateScore(): Int {
        return cards.filterNot { it.rank == ACE }
            .sumOf { it.rank.score }
    }
}

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
            Cards(listOf(Card(Rank(number), SPADE))).calculateScore() shouldBe number.toInt()
        }
    }

    "랭크가 J,Q,K 이면 점수는 10점으로 계산된다" {
        listOf("J", "Q", "K").forAll { face ->
            Cards(listOf(Card(Rank(face), SPADE))).calculateScore() shouldBe 10
        }
    }
})
