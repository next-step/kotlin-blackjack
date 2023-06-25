package blackjack.domain

import blackjack.enums.Symbol
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드의 심볼은 다이아몬드, 하트, 스페이드, 클로버 (4가지)를 가진다`() {
        val symbolList = Symbol.values()

        symbolList.size shouldBe 4
        symbolList shouldContainAll listOf(Symbol.HEARTS, Symbol.DIAMONDS, Symbol.CLUBS, Symbol.SPADES)
    }
}