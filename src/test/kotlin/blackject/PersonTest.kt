package blackject

import blackject.model.Person
import blackject.model.Rule
import blackject.model.card.Card
import blackject.model.card.CardNumber
import blackject.model.card.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PersonTest {

    @Test
    @DisplayName("참가자 카드의 총합 구하는 메소드 확인")
    fun `check total card number`() {
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.SEVEN),
            Card(CardType.CLOVER, CardNumber.TWO),
            Card(CardType.CLOVER, CardNumber.THREE)
        )
        val expectedValue = 12

        val total = Person.getTotalCount(cards)

        assertThat(total).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("참가자 카드의 총합이 21을 넘기는지 확인")
    fun `check that exceed max number of total number`() {
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.SEVEN),
            Card(CardType.CLOVER, CardNumber.TWO),
            Card(CardType.CLOVER, CardNumber.THREE)
        )
        val expectedValue = false

        val total = Person.getTotalCount(cards)
        val isExceed = Person.exceedMaximumCardNumber(Rule.MAX_TOTAL_NUMBER, total)

        assertThat(isExceed).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("게임이 끝난 후 최종 결과 숫자 확인")
    fun `check result number of game`() {
        val cards = mutableListOf(
            Card(CardType.CLOVER, CardNumber.SEVEN),
            Card(CardType.CLOVER, CardNumber.TWO),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val expectedValue = 20

        val person = Person(name = "test", _cardList = cards)
        val total = person.getResultNumber(Rule.MAX_TOTAL_NUMBER)

        assertThat(total).isEqualTo(expectedValue)
    }
}
