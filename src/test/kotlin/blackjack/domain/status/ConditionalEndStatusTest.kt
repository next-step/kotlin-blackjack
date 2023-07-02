package blackjack.domain.status

import blackjack.domain.user.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ConditionalEndStatusTest : StringSpec({
    "카드 점수 합의 모든 경우 수가 21점이 초과된 경우, BURST 상태 조건이 true로 반환된다" {
        val pointResult = Player.PointResult(22, 22)
        ConditionalEndStatus.BURST.isMatch(pointResult) shouldBe true
    }

    "카드 점수 합이 max 경우의 수만 21점이 초과된 경우, BURST 상태 조건이 false로 반환된다" {
        val pointResult = Player.PointResult(20, 22)
        ConditionalEndStatus.BURST.isMatch(pointResult) shouldBe false
    }

    "카드 점수 합이 모든 경우의 수가 21점 미만인 경우, BURST 상태 조건이 false로 반환된다" {
        val pointResult = Player.PointResult(20, 20)
        ConditionalEndStatus.BURST.isMatch(pointResult) shouldBe false
    }

    "카드 점수 합이 모든 경우의 수가 21인 경우, BLACK_JACK 상태 조건이 true로 반환된다" {
        val pointResult = Player.PointResult(21, 21)
        ConditionalEndStatus.BLACK_JACK.isMatch(pointResult) shouldBe true
    }

    "카드 점수 합의 min 경우의 수가 21인 경우, BLACK_JACK 상태 조건이 true로 반환된다" {
        val pointResult = Player.PointResult(21, 20)
        ConditionalEndStatus.BLACK_JACK.isMatch(pointResult) shouldBe true
    }

    "카드 점수 합의 max 경우의 수가 21인 경우, BLACK_JACK 상태 조건이 true로 반환된다" {
        val pointResult = Player.PointResult(20, 21)
        ConditionalEndStatus.BLACK_JACK.isMatch(pointResult) shouldBe true
    }

    "카드 점수 합의 모든 경우의 수가 21이 아닌 경우, BLACK_JACK 상태 조건이 false로 반환된다" {
        val pointResult = Player.PointResult(20, 20)
        ConditionalEndStatus.BLACK_JACK.isMatch(pointResult) shouldBe false
    }
})
