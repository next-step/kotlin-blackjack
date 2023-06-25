package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱은 전체 카드 (각 심볼 당 Ace ~ King - 13개씩 - 총 52개)를 생성한다`() {
        val deck = Deck()
        deck.currentCardCount() shouldBe 52
    }
}
