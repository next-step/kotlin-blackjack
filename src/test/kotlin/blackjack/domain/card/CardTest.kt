package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드에는 모양과 숫자가 있다`() {
        val card = Card(CardKind.SPADE, CardNumber.TEN)
        card.kind shouldBe CardKind.SPADE
        card.number shouldBe CardNumber.TEN
    }
}
