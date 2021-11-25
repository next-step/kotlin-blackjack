package blackject

import blackject.model.Dealer
import blackject.model.ResultType
import blackject.model.card.Card
import blackject.model.card.CardNumber
import blackject.model.card.CardType
import blackject.model.card.Cards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DealerTest {
    @Test
    @DisplayName("딜러의 이름 확인")
    fun `check name of dealer`() {
        val expectedValue = "딜러"

        val dealer = Dealer()

        Assertions.assertThat(dealer.name).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16인 경우 추가적으로 뽑을 수 있다")
    fun `check condition of taking card when dealer has 10`() {
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.SIX),
                Card(CardType.CLOVER, CardNumber.JACK)
            )
        )
        val dealer = Dealer(cards = cards)
        val expectedValue = true

        Assertions.assertThat(dealer.canTakeMoreCard()).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("딜러의 카드가 17인 경우 추가적으로 카드를 뽑을 수 없다.")
    fun `check condition of taking card when dealer has over 16`() {
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.SEVEN),
                Card(CardType.CLOVER, CardNumber.TEN),
            )
        )
        val dealer = Dealer(cards = cards)
        val expectedValue = false

        Assertions.assertThat(dealer.canTakeMoreCard()).isEqualTo(expectedValue)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    @DisplayName("딜러가 bust 경우 게임 결과 확인")
    fun `check game result when dealer bust`(option: Boolean) {
        val result = ResultType.Bust
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.JACK),
                Card(CardType.CLOVER, CardNumber.THREE),
                Card(CardType.CLOVER, CardNumber.TEN),
            )
        )
        val dealer = Dealer(cards = cards)

        dealer.calculateGameResult(17, isDealerBust = option, isDealerBlackJack = option)

        Assertions.assertThat(dealer.result).isEqualTo(result)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    @DisplayName("딜러가 blackjack한 경우 게임 결과 확인")
    fun `check game result when dealer blackjack`(option: Boolean) {
        val result = ResultType.BlackJack
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.JACK),
                Card(CardType.CLOVER, CardNumber.ACE),
            )
        )
        val dealer = Dealer(cards = cards)

        dealer.calculateGameResult(21, isDealerBust = option, isDealerBlackJack = option)

        Assertions.assertThat(dealer.result).isEqualTo(result)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    @DisplayName("딜러가 이긴 경우 게임 결과 확인")
    fun `check game result when dealer win`(option: Boolean) {
        val result = ResultType.Win
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.THREE),
                Card(CardType.CLOVER, CardNumber.JACK),
                Card(CardType.CLOVER, CardNumber.ACE),
            )
        )
        val dealer = Dealer(cards = cards)

        dealer.calculateGameResult(14, isDealerBust = option, isDealerBlackJack = option)

        Assertions.assertThat(dealer.result).isEqualTo(result)
    }

    @ValueSource(booleans = [true, false])
    @ParameterizedTest
    @DisplayName("딜러가 진 경우 게임 결과 확인")
    fun `check game result when dealer lose`(option: Boolean) {
        val result = ResultType.Lose
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.THREE),
                Card(CardType.CLOVER, CardNumber.ACE),
            )
        )
        val dealer = Dealer(cards = cards)

        dealer.calculateGameResult(18, isDealerBust = option, isDealerBlackJack = option)

        Assertions.assertThat(dealer.result).isEqualTo(result)
    }
}
