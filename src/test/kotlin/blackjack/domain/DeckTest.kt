package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `Deck이 초기화되면 활용할 카드가 52장 존재한다`() {
        // given, when
        val deck = Deck()
        // then
        Deck.TOTAL_CARD_SIZE shouldBe deck.cardSize
    }

    @Test
    fun `Deck에 준비된 카드가 모두 소진된 경우 IllegalStateException이 발생한다`() {
        // given
        val deck = Deck()
        repeat(Deck.TOTAL_CARD_SIZE) {
            deck.draw()
        }

        shouldThrow<IllegalStateException> { // then
            deck.draw() // when
        }
    }

    @Test
    fun `Deck에서 필요한 개수만큼 카드를 뽑을 수 있다`() {
        // given
        val deck = Deck()
        val numOfDraw = 3
        // when
        val cards = deck.draw(numOfDraw)
        // then
        numOfDraw shouldBe cards.size
    }
}
