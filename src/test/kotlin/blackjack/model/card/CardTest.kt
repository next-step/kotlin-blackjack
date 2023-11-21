package blackjack.model.card

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드 번호와 모양을 가지는 카드 객체를 생성한다`() {
        // given & when
        val card = Card(CardNumber.ACE, CardSuit.다이아몬드)

        // then
        card.shouldBeInstanceOf<Card>()
    }

    @Test
    fun `카드 객체는 카드의 점수를 가진다`() {
        // given & when
        val card = Card(CardNumber.ACE, CardSuit.다이아몬드)

        // then
        card.score shouldBe 11
    }
}
