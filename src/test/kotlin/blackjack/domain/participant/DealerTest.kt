package blackjack.domain.participant

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    internal fun `딜러라는 이름을 가진 객체가 생성된다`() {
        Dealer().name shouldBe "딜러"
    }
}
