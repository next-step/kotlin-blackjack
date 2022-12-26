import model.Card
import model.CardNumber
import model.CardNumberCalculator
import model.CardShape
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class BlackjackAllTest {
    @Test
    fun `카드 숫자가 문자열로 변환된다`() {
        assertAll(
            {
                assertEquals("K", CardNumber.convertToString(CardNumber.KING))
            },
            {
                assertEquals("5", CardNumber.convertToString(CardNumber.FIVE))
            }
        )
    }

    @Test
    fun `카드 모양이 한글 문자열로 변환된다`() {
        assertAll(
            {
                assertEquals("다이아몬드", CardShape.convertToString(CardShape.DIAMONDS))
            },
            {
                assertEquals("스페이드", CardShape.convertToString(CardShape.SPADES))
            }
        )
    }

    @Test
    fun `중복 없이 카드를 뽑는다`() {
        val cards = mutableListOf<Card>()
        for (i in TOTAL_CARD_SIZE) {
            cards.add(Card.generate())
        }
        assertThat(cards.toSet().size).isSameAs(52)
    }

    @Test
    fun `카드 숫자 합이 21이하 인지 판단한다`() {
        assertAll(
            {
                Assertions.assertThat(
                    CardNumberCalculator().isUnderTwentyOne(
                        listOf(
                            Card(
                                CardNumber.FIVE,
                                CardShape.SPADES
                            )
                        )
                    )
                ).isSameAs(true)
            },
            {
                Assertions.assertThat(
                    CardNumberCalculator().isUnderTwentyOne(
                        listOf(
                            Card(CardNumber.TEN, CardShape.SPADES),
                            Card(CardNumber.KING, CardShape.HEARTS),
                            Card(CardNumber.ACE, CardShape.CLUBS)
                        )
                    )
                ).isSameAs(false)
            }
        )
    }

    companion object {
        private val TOTAL_CARD_SIZE = 1..52
    }
}
