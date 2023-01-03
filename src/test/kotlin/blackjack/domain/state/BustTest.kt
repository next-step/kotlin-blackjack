package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

internal class BustTest {
    @Test
    fun `Bust이면 카드를 더 받을수 없습니다`() {
        val firstCard = Card(Shape.CLUB, Denomination.SEVEN)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = Bust(Hand(firstCard, secondCard))

        shouldThrow<IllegalStateException> { actual.draw(firstCard) }.shouldHaveMessage("카드를 더 받을 수 없습니다.")
    }

    @Test
    fun `Bust는 완료된 상태입니다`() {
        val firstCard = Card(Shape.CLUB, Denomination.SEVEN)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = Bust(Hand(firstCard, secondCard))

        actual.isFinished() shouldBe true
    }

    @Test
    fun `Bust의 수익률은 배팅금액의 마이너스입니다`() {
        val firstCard = Card(Shape.CLUB, Denomination.SEVEN)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = Bust(Hand(firstCard, secondCard))

        actual.earningRate() shouldBe -1.0
    }
}
