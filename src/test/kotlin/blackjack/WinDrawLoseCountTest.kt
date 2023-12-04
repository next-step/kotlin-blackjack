package blackjack

import blackjack.domain.model.WinDrawLoseCount
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class WinDrawLoseCountTest : StringSpec({

    "승무패의 총 숫자의 합이 0보다 같거나 작으면 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            WinDrawLoseCount.of(0, 0, 0)
        }
    }

    "승무패의 총 숫자의 합이 0보다 크면 정상적으로 승무패를 생성한다." {
        val winDrawLoseCount = WinDrawLoseCount.of(1, 2, 3)
        winDrawLoseCount.winCount shouldBe 1
        winDrawLoseCount.drawCount shouldBe 2
        winDrawLoseCount.loseCount shouldBe 3
    }
})
