package blackjack.domain

import blackjack.card.CardDeck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `카드 덱은 생성할 때 52장의 카드를 가진다`() {
        val card = CardDeck
        card.getSize() shouldBe 52
    }

    @Test
    fun `카드 덱 전달받은 숫자만큼 카드를 뽑을 수 있다`() {
        val deck = CardDeck
        val drawNumber = 2
        val card = deck.draw(drawNumber)
        card.size shouldBe drawNumber
    }
}
