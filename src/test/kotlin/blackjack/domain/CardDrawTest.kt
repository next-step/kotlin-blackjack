package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardDrawTest {
    @Test
    fun `블랙잭 게임은 총 52장의 카드단위로 게임을 한다`() {
        val blackJack = CardDraw.init().cards
        blackJack.cards.size.shouldBe(52)
    }

    @Test
    fun `카드를 나눠주면 전체 카드목록 사이즈가 줄어든다`() {
        val blackJack = CardDraw.init().cards
        blackJack.getCard()
        blackJack.cards.size.shouldBe(51)
    }
}
