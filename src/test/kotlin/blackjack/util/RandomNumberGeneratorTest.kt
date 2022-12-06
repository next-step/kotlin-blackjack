package blackjack.util

import blackjack.domain.util.RandomNumberGenerator
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.property.checkAll

internal class RandomNumberGeneratorTest : FreeSpec({
    "랜덤한 값은 항상 주어진 범위 안에 나온다." {
        checkAll<Int> { _ ->
            RandomNumberGenerator.generate(0..10) shouldBeInRange 0..10
        }

        checkAll<Int> { _ ->
            RandomNumberGenerator.generate(0..100) shouldBeInRange 0..100
        }
    }
})
