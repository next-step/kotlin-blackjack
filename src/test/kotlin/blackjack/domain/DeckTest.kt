package blackjack.domain

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class DeckTest {
    @Test
    fun `초기화 된 덱은 52개의 카드를 갖고 있다`() {
        val deck = Deck()
        deck.size shouldBe 52
    }

    @Test
    fun `덱에서 카드를 뽑으면 사이즈가 1 감소한다`() {
        val deck = Deck()

        deck.draw()

        deck.size shouldBe 51
    }

    @Test
    fun `덱에서 카드를 뽑으면 카드가 뽑힌다`() {
        val deck = Deck()

        val card = deck.draw()

        card shouldNotBe null
        card.rankValue shouldBeGreaterThan 0
    }

    @Test
    fun `카드를 52변 뽑으면 각기 다른 52개의 카드들이 뽑힌다`() {
        val deck = Deck()

        val drawnCards =
            (1..52)
                .map { deck.draw() }
                .toSet()

        drawnCards shouldHaveSize 52
    }

    @Test
    fun `카드를 뽑은 횟수가 52번을 초가하면 null 이 반환된다`() {
        val deck = Deck()
        (1..52)
            .map { deck.draw() }
        assertThrows<IllegalStateException> { deck.draw() }
    }
}
