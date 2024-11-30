package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class RankTest : StringSpec({
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

    "랭크가 J,Q,K 이면 점수는 10점으로 계산된다" {
        listOf("J", "Q", "K").forAll { face ->
            Rank(face).score shouldBe 10
        }
    }

    "랭크가 에이스이면 기본점수는 11점이다" {
        Rank("A").score shouldBe 11
    }
})
