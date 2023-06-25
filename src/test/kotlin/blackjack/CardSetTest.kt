package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CardSetTest {
    @Test
    internal fun `카드세트는 초기 생성시 52장의 카드를 가진다`() {
        val sut = CardSet()
        sut.size shouldBe 52
    }

    @Test
    internal fun `카드세트에서 카드를 한장씩 뽑을 수 있다`() {
        val sut = CardSet()
        val card : Card = sut.pop()
        println(card)
        sut.size shouldBe 51
    }
}
