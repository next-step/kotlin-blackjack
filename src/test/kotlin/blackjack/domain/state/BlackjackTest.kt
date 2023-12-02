package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardCharacter
import blackjack.domain.card.CardShape
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackjackTest {
    @Test
    fun `Blackjack 상태에서 한장을 더 뽑으려고 하면 IllegalStateException 발생한다`() {
        val blackjack = Blackjack()
        assertThrows<IllegalStateException> { blackjack.draw(Card(CardCharacter.KING, CardShape.CLUB)) }
    }


    @Test
    fun `블랙잭은 배팅 금액`() {
        val blackjack = Blackjack()
        val actual = blackjack.profit(100)
        actual shouldBe 150
    }
}
