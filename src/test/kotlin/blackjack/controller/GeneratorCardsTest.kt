package blackjack.controller

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GeneratorCardsTest {
    @Test
    fun `모든 카드(52)에 대해서 리스트를 구성한다`() {
        val deck = GeneratorCards().generateCardDeck()
        deck.size shouldBe 52
    }

    @Test
    fun `카드 한 장 방출 후 카드 스택에서 제거한다`() {
        val deck = GeneratorCards().generateCardDeck()
        println(deck.pop())
        deck.size shouldBe 51
    }
}
