package blackjack.domain.score

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScoreTest : StringSpec({
    "isGreaterThanMaxScore 는 현재 점수에서 한 개의 카드 숫자를 더한 값이 최고 점수보다 크면 참이다." {
        val score = Score(15)
        score.isGreaterThanMaxScore(10) shouldBe true
    }

    "isBlackJack 은 현재 점수가 21점 이면 참이다" {
        val score = Score(21)
        score.isBlackJack() shouldBe true
    }
})
