package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BurstTest : FunSpec({

    test("Burst 인지 반환한다") {
        Burst.isBurst() shouldBe true
    }
})
