package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test
import java.util.LinkedList

class GameCardsTest {

    @Test
    fun `nextCard는 가장 앞에있는 카드 한장을 return 해야 한다`() {
        val gameCards = GameCards(
            listOf(
                Card(CardSuite.DIAMOND, CardNumber.ACE),
                Card(CardSuite.DIAMOND, CardNumber.QUEEN)
            ).toCollection(LinkedList())
        )

        assertThat(gameCards.nextCard())
            .isEqualTo(Card(CardSuite.DIAMOND, CardNumber.ACE))
    }

    @Test
    fun `카드가 없으면 IllegalStateException 발생해야 한다`() {
        val gameCards = GameCards(
            LinkedList()
        )

        assertThatIllegalStateException().isThrownBy { gameCards.nextCard() }
    }

    @Test
    fun `카드를 제거하면 다음 카드가 나와야 한다`() {
        val gameCards = GameCards(
            listOf(
                Card(CardSuite.DIAMOND, CardNumber.ACE),
                Card(CardSuite.DIAMOND, CardNumber.QUEEN)
            ).toCollection(LinkedList())
        )

        assertThat(gameCards.nextCard())
            .isEqualTo(Card(CardSuite.DIAMOND, CardNumber.ACE))

        gameCards.removeFront()

        assertThat(gameCards.nextCard())
            .isEqualTo(Card(CardSuite.DIAMOND, CardNumber.QUEEN))
    }
}
