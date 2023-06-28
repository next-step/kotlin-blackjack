package blackjack

import blackjack.domain.CardType
import blackjack.domain.NumberType
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드는 4가지 타입을 가진다`() {
        val cardType = CardType.values()
        cardType.size shouldBe 4
    }

    @Test
    fun `카드의 숫자는 13종류이다`() {
        val numberType = NumberType.values()
        numberType.size shouldBe 13
    }
}