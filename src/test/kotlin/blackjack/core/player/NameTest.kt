package blackjack.core.player

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class NameTest {
    @Test
    fun `생성자 테스트`() {
        Name("abc").toString() shouldBe "abc"
        Name("  abc").toString() shouldBe "abc"
        Name("abc  ").toString() shouldBe "abc"

        shouldThrow<IllegalArgumentException> { Name(" ") }
        shouldThrow<IllegalArgumentException> { Name("") }
        shouldThrow<IllegalArgumentException> { Name("    ") }
    }
}