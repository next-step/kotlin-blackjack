package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardCharacter
import blackjack.domain.card.CardShape
import blackjack.domain.card.Hands
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BustTest {
    @Test
    fun `Bust는 한장을 더 뽑으려고 하면 IllegalStateException 발생한다`() {
        val bust = Bust(hands = Hands(Card(CardCharacter.TEN, CardShape.CLUB), Card(CardCharacter.KING, CardShape.CLUB)))
        assertThrows<IllegalStateException> { bust.draw(Card(CardCharacter.KING, CardShape.CLUB)) }
    }

    @Test
    fun `Bust는 배팅 금액을 모두 잃는다`() {
        val bust = Bust(hands = Hands(Card(CardCharacter.TEN, CardShape.CLUB), Card(CardCharacter.KING, CardShape.CLUB)))
        val actual = bust.profit(1000)
        actual shouldBe 0
    }
}
