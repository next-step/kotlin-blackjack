package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

internal class CardPackTest {
    @Test
    fun `카드 52장을 만들어서 뽑는다`() {
        val cardPack = CardPack()

        val cardSet = mutableSetOf<Card>()
        repeat(52) {
            val card = cardPack.pickCard()
            cardSet.add(card)
        }
        Assertions.assertThat(cardSet).hasSize(52)
    }

    @Test
    fun `카드 53번 째, 카드를 뽑으면 에러`() {
        val cardPack = CardPack(isEmpty = true)

        Assertions.assertThatThrownBy { cardPack.pickCard() }
            .isInstanceOf(IllegalStateException::class.java)
    }
}
