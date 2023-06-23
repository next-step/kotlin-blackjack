package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeckTest {
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck
    }

    @Test
    fun `게임 카드 세트에서 카드를 뽑을 수 있다`() {
        // when
        val draw = deck.draw()

        // then
        (draw is Card) shouldBe true
    }

    @Test
    fun `게임 카드 세트에서 모든 카드를 뽑으면 null이 반환된다`() {
        // given
        while (true) {
            val draw = deck.draw() ?: break
        }

        // when
        val draw = deck.draw()

        // then
        draw shouldBe null
    }
}
