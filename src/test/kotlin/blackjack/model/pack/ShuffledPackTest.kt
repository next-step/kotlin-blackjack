package blackjack.model.pack

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec

class ShuffledPackTest : StringSpec({

    "랜덤한 카드를 뽑을 수 있다" {
        shouldNotThrow<IllegalArgumentException> {
            (1..10).forEach { _ ->
                ShuffledPack.pickCard()
            }
        }
    }

})
