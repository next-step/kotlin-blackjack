package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CardSetTest {
    @Test
    internal fun `카드세트는 초기 생성시 52장의 카드를 가진다`() {
        val sut = CardSet()
        sut.size shouldBe 52
    }

    @Test
    internal fun `카드세트에서 카드를 한장씩 뽑을 수 있다`() {
        val sut = CardSet()
        val card: Card = sut.pop()
        println(card)
        sut.size shouldBe 51
    }

    @Test
    internal fun `카드세트에서 카드를 전부 뽑았을때 다시 뽑으면 예외가 발생한다`() {
        val sut = CardSet()
        repeat(52) { sut.pop() }
        assertThrows<IllegalStateException> { sut.pop() }
    }
}
