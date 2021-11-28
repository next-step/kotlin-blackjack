package blackject

import blackject.model.Amount
import blackject.model.Person
import blackject.model.ResultType
import blackject.model.card.Card
import blackject.model.card.CardNumber
import blackject.model.card.CardType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    @DisplayName("참가자의 카드 총합이 21을 넘지 않은 경우 카드를 더 뽑을 수 있는지 확인")
    fun `check if take more card when person has less than 21`() {
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.SEVEN),
            Card(CardType.CLOVER, CardNumber.TWO),
            Card(CardType.CLOVER, CardNumber.THREE)
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)
        val expectedValue = true

        val isTakeMoreCard = person.canTakeMoreCard()

        Assertions.assertThat(isTakeMoreCard).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("참가자의 카드 총합이 21을 넘는 경우 카드를 더 뽑을 수 있는지 확인")
    fun `check if take more card when person has more than 21`() {
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.EIGHT),
            Card(CardType.CLOVER, CardNumber.SEVEN),
            Card(CardType.CLOVER, CardNumber.TWO),
            Card(CardType.CLOVER, CardNumber.JACK)
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)
        val expectedValue = false

        val isTakeMoreCard = person.canTakeMoreCard()

        Assertions.assertThat(isTakeMoreCard).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("참가자의 카드 총합이 21인 경우 카드를 더 뽑을 수 있는지 확인")
    fun `check if take more card when person has 21`() {
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.JACK),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)
        val expectedValue = false

        val isTakeMoreCard = person.canTakeMoreCard()

        Assertions.assertThat(isTakeMoreCard).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("참가자가 블랙잭인지 여부 판단")
    fun `check if user blackjack`() {
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.JACK),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)
        val expectedValue = true

        val isBlackJack = person.isBlackJack()

        Assertions.assertThat(isBlackJack).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("딜러가 bust하여 게임이 끝난 경우의 참가자의 게임 결과 확인")
    fun `check game result when dealer bust`() {
        val result = ResultType.Win
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.JACK),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)

        person.calculateGameResult(10, isDealerBust = true, isDealerBlackJack = false)

        Assertions.assertThat(person.result).isEqualTo(result)
    }

    @Test
    @DisplayName("참가자가 블랙잭인 경우 게임 결과 확인")
    fun `check game result when participant blackjack`() {
        val result = ResultType.BlackJack
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.JACK),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)

        person.calculateGameResult(21, isDealerBust = false, isDealerBlackJack = false)

        Assertions.assertThat(person.result).isEqualTo(result)
    }

    @Test
    @DisplayName("참가자가 bust 경우 게임 결과 확인")
    fun `check game result when participant bust`() {
        val result = ResultType.Bust
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.JACK),
            Card(CardType.CLOVER, CardNumber.THREE),
            Card(CardType.CLOVER, CardNumber.TEN),
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)

        person.calculateGameResult(17, isDealerBust = false, isDealerBlackJack = false)

        Assertions.assertThat(person.result).isEqualTo(result)
    }

    @Test
    @DisplayName("참가자가 딜러와 함꼐 blackjack한 경우 게임 결과 확인")
    fun `check game result when participant blackjack with dealer`() {
        val result = ResultType.Win
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.JACK),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)

        person.calculateGameResult(21, isDealerBust = false, isDealerBlackJack = true)

        Assertions.assertThat(person.result).isEqualTo(result)
    }

    @Test
    @DisplayName("참가자가 이긴 경우 게임 결과 확인")
    fun `check game result when participant win`() {
        val result = ResultType.Win
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.THREE),
            Card(CardType.CLOVER, CardNumber.JACK),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)

        person.calculateGameResult(14, isDealerBust = false, isDealerBlackJack = false)

        Assertions.assertThat(person.result).isEqualTo(result)
    }

    @Test
    @DisplayName("참가자가 진 경우 게임 결과 확인")
    fun `check game result when participant lose`() {
        val result = ResultType.Lose
        val cards = listOf(
            Card(CardType.CLOVER, CardNumber.THREE),
            Card(CardType.CLOVER, CardNumber.ACE),
        )
        val person = Person(name = "이소현")
        person.giveCard(cards)

        person.calculateGameResult(15, isDealerBust = false, isDealerBlackJack = false)

        Assertions.assertThat(person.result).isEqualTo(result)
    }

    @Test
    @DisplayName("게임에서 이긴 사용자의 수익률 계산")
    fun `check profit of win user`() {
        val money = Amount(100.0)
        val result = ResultType.Win

        val person = Person("소현", money)
        person.changeResultType(result)
        val profit = person.getProfit()

        Assertions.assertThat(profit).isEqualTo(100)
    }

    @Test
    @DisplayName("게임에서 진 사용자의 수익률 계산")
    fun `check profit of lose user`() {
        val money = Amount(100.0)
        val result = ResultType.Lose

        val person = Person("소현", money)
        person.changeResultType(result)
        val profit = person.getProfit()

        Assertions.assertThat(profit).isEqualTo(-100)
    }

    @Test
    @DisplayName("BlackJack 사용자의 수익률 계산")
    fun `check profit of blackjack user`() {
        val money = Amount(100.0)
        val result = ResultType.BlackJack

        val person = Person("소현", money)
        person.changeResultType(result)
        val profit = person.getProfit()

        Assertions.assertThat(profit).isEqualTo(150)
    }

    @Test
    @DisplayName("Bust 사용자의 수익률 계산")
    fun `check profit of bust user`() {
        val money = Amount(100.0)
        val result = ResultType.Bust

        val person = Person("소현", money)
        person.changeResultType(result)
        val profit = person.getProfit()

        Assertions.assertThat(profit).isEqualTo(-100)
    }
}
