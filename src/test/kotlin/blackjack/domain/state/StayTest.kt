package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

internal class StayTest {
    @Test
    fun `Stay이면 카드를 더 받을수 없습니다`() {
        val firstCard = Card(Shape.CLUB, Denomination.SEVEN)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = Stay(Hand(firstCard, secondCard))

        shouldThrow<IllegalStateException> { actual.draw(firstCard) }.shouldHaveMessage("카드를 더 받을 수 없습니다.")
    }

    @Test
    fun `Stay는 완료된 상태입니다`() {
        val firstCard = Card(Shape.CLUB, Denomination.SEVEN)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = Stay(Hand(firstCard, secondCard))

        actual.isFinished() shouldBe true
    }

    @Test
    fun `Stay의 수익계산식은 베팅금액 그대로입니다`() {
        val firstCard = Card(Shape.CLUB, Denomination.SEVEN)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = Stay(Hand(firstCard, secondCard))

        actual.earningRate() shouldBe 1.0
    }
}
