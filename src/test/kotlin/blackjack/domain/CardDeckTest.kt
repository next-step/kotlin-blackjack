package blackjack.domain

import blackjack.card.CardDeck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `카드 덱은 생성할 때 52장의 카드를 가진다`() {
        val card = CardDeck()
        card.getSize() shouldBe 52
    }
}
