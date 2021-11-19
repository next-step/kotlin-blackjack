package blackject

import blackject.model.Person
import blackject.model.Rule
import blackject.model.card.Card
import blackject.model.card.CardNumber
import blackject.model.card.CardType
import blackject.model.card.Cards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    @DisplayName("참가자의 카드 총합이 21을 넘지 않은 경우 카드를 더 뽑을 수 있는지 확인")
    fun `check if take more card when person has less than 21`() {
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.SEVEN),
                Card(CardType.CLOVER, CardNumber.TWO),
                Card(CardType.CLOVER, CardNumber.THREE)
            )
        )
        val person = Person(name = "이소현", cards = cards)
        val expectedValue = true

        val isTakeMoreCard = person.isTakeMoreCard(Rule.MAX_TOTAL_NUMBER, Rule.EXCEPT_NUMBER)

        Assertions.assertThat(isTakeMoreCard).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("참가자의 카드 총합이 21을 넘는 경우 카드를 더 뽑을 수 있는지 확인")
    fun `check if take more card when person has more than 21`() {
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.EIGHT),
                Card(CardType.CLOVER, CardNumber.SEVEN),
                Card(CardType.CLOVER, CardNumber.TWO),
                Card(CardType.CLOVER, CardNumber.JACK)
            )
        )
        val person = Person(name = "이소현", cards = cards)
        val expectedValue = false

        val isTakeMoreCard = person.isTakeMoreCard(Rule.MAX_TOTAL_NUMBER, Rule.EXCEPT_NUMBER)

        Assertions.assertThat(isTakeMoreCard).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("참가자의 카드 총합이 21인 경우 카드를 더 뽑을 수 있는지 확인")
    fun `check if take more card when person has 21`() {
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.JACK),
                Card(CardType.CLOVER, CardNumber.ACE),
            )
        )
        val person = Person(name = "이소현", cards = cards)
        val expectedValue = false

        val isTakeMoreCard = person.isTakeMoreCard(Rule.MAX_TOTAL_NUMBER, Rule.EXCEPT_NUMBER)

        Assertions.assertThat(isTakeMoreCard).isEqualTo(expectedValue)
    }
}
