package blackjack.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

internal class DeckTest {
    @Test
    fun `트럼프 카드 네개의 모양과 A - K 까지 52장으로 구성된다`() {
        val deck = Deck()

        deck.cards.count() shouldBe 52
    }

    @Test
    fun `덱에서 카드를 뽑으면 카드 숫자는 줄어든다`() {
        val deck = Deck()
        val firstCard = deck.draw()
        val secondCard = deck.draw()

        firstCard shouldNotBe secondCard
        deck.cards.count() shouldBe 50
    }
}
