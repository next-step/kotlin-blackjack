package blackjack

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class BlackjackShoeTest {
    @Test
    internal fun `슈의 개수는 0보다 크고 8보다 작거나 같아야 한다`() {
        shouldThrow<IllegalArgumentException> {
            BlackjackShoe(0)
        }
        shouldThrow<IllegalArgumentException> {
            BlackjackShoe(9)
        }
    }
}
