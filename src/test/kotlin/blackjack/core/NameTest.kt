package blackjack.core

import blackjack.core.player.Name
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class NameTest {
    @Test
    fun `Class 생성을 테스트한다`() {
        Name("abc").toString() shouldBe "abc"
        Name("  abc").toString() shouldBe "abc"
        Name("abc  ").toString() shouldBe "abc"

        shouldThrow<IllegalArgumentException> { Name("") }
        shouldThrow<IllegalArgumentException> { Name("  ") }
    }
}
