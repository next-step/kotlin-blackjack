package next.step.blackjack.domain.card.state

import io.kotest.core.spec.style.DescribeSpec
import next.step.blackjack.domain.card.Cards
import org.junit.jupiter.api.assertThrows

class BlackjackStateTest : DescribeSpec({

    describe("BlackjackState") {
        context("next") {
            it("항상 예외 발생") {
                assertThrows<IllegalArgumentException> { BlackjackState.next(Cards.of(emptyList())) }
            }
        }
    }
})
