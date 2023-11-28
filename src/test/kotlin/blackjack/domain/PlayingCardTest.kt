package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayingCardTest {
    @Test
    fun `카드는 중복없이 52장으로 구성된다`() {
        PlayingCard().cards.size shouldBe 52
    }
}
