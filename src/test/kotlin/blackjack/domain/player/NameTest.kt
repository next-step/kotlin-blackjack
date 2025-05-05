package blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll

class NameTest : FunSpec({
    test("should not be empty") {
        listOf("", " ", "     ").forAll {
            shouldThrow<IllegalArgumentException> {
                Name(it)
            }
        }
    }
})
