package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardCharacter
import blackjack.domain.card.CardShape
import blackjack.domain.card.Hands
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StayTest {
    @Test
    fun `Stay는 한장을 더 뽑으려고 하면 IllegalStateException 발생한다`() {
        val stay = Stay(hands = Hands())
        assertThrows<IllegalStateException> { stay.draw(Card(CardCharacter.KING, CardShape.CLUB)) }
    }

    @Test
    fun `Stay는 승리 시 배팅 금액을 그대로 받는다`() {
        val stay = Stay(hands = Hands())
        val actual = stay.profit(1000)
        actual shouldBe 1000
    }
}
