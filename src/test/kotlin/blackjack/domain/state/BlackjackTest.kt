package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

internal class BlackjackTest {
    @Test
    fun `Blackjack이면 카드를 더 받을수 없습니다`() {
        val firstCard = Card(Shape.CLUB, Denomination.ACE)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = Blackjack(Hand(firstCard, secondCard))

        shouldThrow<IllegalStateException> { actual.draw(firstCard) }.shouldHaveMessage("카드를 더 받을 수 없습니다.")
    }

    @Test
    fun `Blackjack은 완료된 상태입니다`() {
        val firstCard = Card(Shape.CLUB, Denomination.ACE)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = Blackjack(Hand(firstCard, secondCard))

        actual.isFinished() shouldBe true
    }

    @Test
    fun `Blackjack의 수익률은 배팅금액의 일점오배다`() {
        val firstCard = Card(Shape.CLUB, Denomination.ACE)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = Blackjack(Hand(firstCard, secondCard))

        actual.profit(10000.0) shouldBe 15000.0
    }
}
