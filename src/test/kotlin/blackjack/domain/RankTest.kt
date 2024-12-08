package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class RankTest : StringSpec({
    "유효하지 않은 랭크값을 통해 랭크 생성시 예외 발생한다" {
        listOf("0", "1", "-1", "B", "D").forAll { invalidRankValue ->
            shouldThrow<IllegalArgumentException> { Rank.from(invalidRankValue) }
        }
    }

    "랭크가 숫자이면 점수는 숫자의 값이다" {
        listOf("2", "3", "4", "5", "6").forAll { number ->
            Rank.from(number).score shouldBe number.toInt()
        }
    }

    "랭크가 J,Q,K 이면 점수는 10점이다" {
        listOf("J", "Q", "K").forAll { face ->
            Rank.from(face).score shouldBe 10
        }
    }

    "랭크가 에이스이면 기본점수는 11점이다" {
        Rank.from("A").score shouldBe 11
    }
})
