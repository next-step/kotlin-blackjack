package domain

import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `카드 덱에서 랜덤 카드가 반환됩니다`() {
        val cardDeck = CardDeck()
        cardDeck.draw() shouldNotBe null
    }
}
