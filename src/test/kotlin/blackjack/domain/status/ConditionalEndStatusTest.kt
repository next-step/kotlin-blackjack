package blackjack.domain.status

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ConditionalEndStatusTest : StringSpec({
    "카드 점수 합의 모든 경우 수가 21점이 초과된 경우, BURST 상태 조건이 true로 반환된다" {
        ConditionalEndStatus.BURST.isMatch(22) shouldBe true
    }

    "카드 점수 합이 모든 경우의 수가 21점 미만인 경우, BURST 상태 조건이 false로 반환된다" {
        ConditionalEndStatus.BURST.isMatch(20) shouldBe false
    }

    "카드 점수 합이 21인 경우, BLACK_JACK 상태 조건이 true로 반환된다" {
        ConditionalEndStatus.BLACK_JACK.isMatch(21) shouldBe true
    }

    "카드 점수 합이 21이 아닌 경우, BLACK_JACK 상태 조건이 false로 반환된다" {
        ConditionalEndStatus.BLACK_JACK.isMatch(20) shouldBe false
    }
})
