package blackjack

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

internal class DeckTest {
    @Test
    fun `트럼프 카드 네개의 모양과 A - K 까지 48장으로 구성된다`() {
        val deck = Deck()

        deck.cards.count() shouldBe 48
    }

    @Test
    fun `셔플된 덱의 위에 존재하는 카드 부터 반환한다`() {
        val deck = Deck()
        val firstCard = deck.draw()
        val secondCard = deck.draw()

        firstCard shouldNotBe secondCard
        deck.cards.count() shouldBe 46
    }
}
