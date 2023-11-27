package blackjack.model.playable

import blackjack.model.player.BlackjackScore
import blackjack.model.result.PlayableResult
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackjackScoreTest : StringSpec({
    "이겨야한다" {
        BlackjackScore(4) vs BlackjackScore(2) shouldBe PlayableResult.WIN
    }

    "져야한다" {
        BlackjackScore(1) vs BlackjackScore(2) shouldBe PlayableResult.LOSE
    }

    "비겨야한다" {
        BlackjackScore(7) vs BlackjackScore(7) shouldBe PlayableResult.DRAW
    }
})
