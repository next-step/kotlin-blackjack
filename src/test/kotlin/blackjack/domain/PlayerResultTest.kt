package blackjack.domain

import blackjack.domain.result.PlayerResult
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerResultTest : StringSpec({
    "플레이어의 결과를 승리로 바꿀 수 있다." {
        val playerResult = PlayerResult()
        playerResult.win()

        playerResult.isWin shouldBe true
    }
})
