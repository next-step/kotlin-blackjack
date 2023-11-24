package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱 생성 시, 52장의 전체 카드를 갖는다`() {
        val deck = Deck()

        deck.size() shouldBe Deck.MAX_CARD_SIZE
    }

    @Test
    fun `덱에서 랜덤으로 카드 한 장을 뽑을 수 있다`() {
        val deck = Deck()
        val firstSize = deck.size()

        val card = deck.draw()

        CardShape.values().contains(card.shape) shouldBe true
        CardCharacter.values().contains(card.character) shouldBe true
        deck.size() shouldBe firstSize - 1
    }

    @Test
    fun `덱에서 카드가 없을 때, draw() 를 시도하면 예외가 발생한다`() {
        val deck = Deck()
        repeat(Deck.MAX_CARD_SIZE) {
            deck.draw()
        }

        deck.size() shouldBe 0
        shouldThrow<IllegalStateException> {
            deck.draw()
        }.message shouldBe "덱에 남은 카드가 없습니다."
    }
}
