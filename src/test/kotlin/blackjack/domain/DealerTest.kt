package blackjack.domain

import blackjack.domain.exceptions.ScoreOverflowException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealerTest {
    @Test
    fun `딜러는 점수가 17 이상이면 더 이상 카드를 받지 못한다`() {
        val dealer = Dealer()
        dealer.takeCards(
            listOf(
                Card(CardSymbol.SPADE, CardNumber.KING),
                Card(CardSymbol.SPADE, CardNumber.SEVEN)
            )
        )

        assertThrows<ScoreOverflowException> {
            dealer.takeCards(
                listOf(Card(CardSymbol.SPADE, CardNumber.ACE))
            )
        }
    }
}
