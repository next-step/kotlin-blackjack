import model.Card
import model.CardNumber
import model.CardShape
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BlackjackAllTest {
    @Test
    fun `카드 숫자가 문자열로 변환된다`() {
        org.junit.jupiter.api.assertAll(
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
        org.junit.jupiter.api.assertAll(
            {
                assertEquals("다이아몬드", CardShape.convertToString(CardShape.DIAMONDS))
            },
            {
                assertEquals("스페이드", CardShape.convertToString(CardShape.SPADES))
            }
        )
    }

    @Test
    fun `중복 없이 카드를 뽑느다`() {
        val cards = mutableListOf<Card>()
        for (i in 1..52) {
            cards.add(Card.generateCard())
        }
        Assertions.assertThat(cards.toSet().size).isSameAs(52)
    }
}
