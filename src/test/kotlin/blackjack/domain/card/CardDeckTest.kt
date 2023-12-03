package blackjack.domain.card

import blackjack.domain.card.CardDeck
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    fun `카드는 중복없이 52장으로 구성된다`() {
        CardDeck().cards.size shouldBe 52
    }

    @Test
    fun `카드덱에서 한장의 카드를 랜덤으로 뽑을 수 있다`() {
        val cardDeck = CardDeck()
        val card = cardDeck.draw()
        card shouldNotBe null
    }

    @Test
    fun `카드덱에서 모든 카드를 뽑은 경우 IllegalArgumentException 이 발생한다`() {
        val cardDeck = CardDeck()
        repeat(52) {
            cardDeck.draw()
        }
        assertThrows<IllegalArgumentException> { cardDeck.draw() }
    }
}
