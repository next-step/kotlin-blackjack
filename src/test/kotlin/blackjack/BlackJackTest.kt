package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Rank
import blackjack.domain.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class BlackJackTest : StringSpec({
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
