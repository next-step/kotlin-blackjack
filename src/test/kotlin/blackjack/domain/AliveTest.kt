package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AliveTest : FunSpec({

    test("Burst 인지 반환한다") {
        Alive.isBurst() shouldBe false
    }
})
