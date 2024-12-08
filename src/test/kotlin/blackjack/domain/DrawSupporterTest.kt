package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DrawSupporterTest : StringSpec({

    "현재 뽑는 순서를 조회한다." {
        val drawSupporter = DrawSupporter()

        val actual = drawSupporter.currentDrawOrder

        actual shouldBe 0
    }

    "뽑는 순서를 타겟 순서에서 1 올린다." {
        val drawSupporter = DrawSupporter()

        drawSupporter.incrementDrawOrder(2, 5)

        drawSupporter.currentDrawOrder shouldBe 3
    }

    "뽑는 순서를 올릴때 플레이어의 수보다 같다면 0으로 초기화한다." {
        val drawSupporter = DrawSupporter()

        drawSupporter.incrementDrawOrder(4, 5)

        drawSupporter.currentDrawOrder shouldBe 0
    }
})
