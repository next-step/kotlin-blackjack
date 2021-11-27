package blackject

import blackject.model.Person
import blackject.model.card.Card
import blackject.model.card.CardNumber
import blackject.model.card.CardType
import blackject.model.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    @DisplayName("참가자에게 카드 추가되는지 확인")
    fun `check add card`() {
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.SEVEN),
            Card(CardType.CLOVER, CardNumber.TWO),
            Card(CardType.CLOVER, CardNumber.THREE)
        )
        val person = Person(name = "이소현",)

        person.giveCard(cards)

        assertThat(person.cards.cardList.size).isEqualTo(3)
        assertThat(person.cards.cardList).isEqualTo(cards)
    }

    @Test
    @DisplayName("카드의 최소 합 확인")
    fun `check result min total sum`() {
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.SEVEN),
            Card(CardType.CLOVER, CardNumber.TWO),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val expectedValue = 10

        val sum = Cards.sum(cards)

        assertThat(sum).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("카드의 최대 합 확인")
    fun `check result max total sum`() {
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.SEVEN),
            Card(CardType.CLOVER, CardNumber.TWO),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val expectedValue = 20

        var sum = Cards.sum(cards)
        sum = sum + CardNumber.getAceMaxNumber() - CardNumber.number(CardNumber.ACE)

        assertThat(sum).isEqualTo(expectedValue)
    }
}
