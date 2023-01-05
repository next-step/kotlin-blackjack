import model.Card
import model.CardNumber
import model.CardNumberCalculator
import model.CardShape
import model.Cards
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
        val cards = Cards()
        for (i in TOTAL_CARD_SIZE) {
            cards.add(Card.generate())
        }
        assertThat(cards.get().toSet().size).isEqualTo(52)
    }

    @Test
    fun `추가 카드 받기 가능 여부를 판단한다`() {
        assertAll(
            {
                assertThat(
                    CardNumberCalculator().isGetExtraCard(
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
                assertThat(
                    CardNumberCalculator().isGetExtraCard(
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

    @Test
    fun `ACE 카드 값을 11로 결정한다`() {
        assertAll(
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(2)).isSameAs(11)
            },
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(10)).isSameAs(11)
            }
        )
    }

    @Test
    fun `ACE 카드 값을 1로 결정한다`() {
        assertAll(
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(11)).isSameAs(1)
            },
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(20)).isSameAs(1)
            }
        )
    }

    companion object {
        private val TOTAL_CARD_SIZE = 1..52
    }
}
