package blackjack.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class WalletTest {

  @Test
  fun `sumUp 은 지갑에 있는 모든 카드의 숫자를 더한 값과 같다`() {
    // given
    val cards = listOf<Card>(Card(Shape.DIAMOND, CardNumber.NINE), Card(Shape.CLOVER, CardNumber.EIGHT), Card(Shape.CLOVER, CardNumber.TWO))
    val testWallet = Wallet(cards)
    val expectedSum = 19

    // when
    val result = testWallet.sumUp

    // then
    Assertions.assertThat(result).isEqualTo(expectedSum)
  }

  @Test
  fun `limit이 21일 때 지갑에 있는 카드의 숫자가 21 이상이면 isAbleToDraw가 false를 리턴한다`() {
    // given
    val cards = listOf<Card>(Card(Shape.DIAMOND, CardNumber.TEN), Card(Shape.CLOVER, CardNumber.TEN), Card(Shape.CLOVER, CardNumber.TEN))
    val testWallet = Wallet(cards)
    val testLimit = 21

    // when
    val result = testWallet.isAbleToDraw(testLimit)

    // then
    Assertions.assertThat(result).isFalse()
  }

  @Test
  fun `limit이 21일 때 지갑에 있는 카드의 숫자가 21 미만이면 isAbleToDraw가 true를 리턴한다`() {
    // given
    val cards = listOf<Card>(Card(Shape.DIAMOND, CardNumber.NINE),  Card(Shape.CLOVER, CardNumber.TWO))
    val testWallet = Wallet(cards)
    val testLimit = 21

    // when
    val result = testWallet.isAbleToDraw(testLimit)

    // then
    Assertions.assertThat(result).isTrue()
  }
}