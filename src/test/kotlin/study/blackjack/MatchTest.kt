package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.Match

/**
 * @author 이상준
 */
class MatchTest : StringSpec({
    "플레이어 승 테스트" {
        Match.of(playerScore = 19, dealerScore = 17) shouldBe Match.WIN
    }
    "플레이어 패배 테스트" {
        Match.of(playerScore = 17, dealerScore = 19) shouldBe Match.LOSE
    }
    "블랙잭 테스트" {
        Match.of(playerScore = 21, dealerScore = 19) shouldBe Match.BLACKJACK
    }
    "무승부 테스트" {
        Match.of(playerScore = 21, dealerScore = 21) shouldBe Match.PUSH
    }
})
