package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({

    "참가자 이름 유효성 체크" - {
        "참가자 이름은 빈 값일 수 없다." {
            val exception = shouldThrow<IllegalArgumentException> { Player("") }

            exception.message shouldBe "참가자 이름은 빈 값일 수 없습니다."
        }
    }
})
