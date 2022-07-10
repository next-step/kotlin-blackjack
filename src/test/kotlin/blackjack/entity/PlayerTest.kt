

package blackjack.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `1장을 드로우 하면 player가 가진 wallet에 카드가 한 장 늘어난다`() {
        // given
        val cards = listOf<Card>(Card(Shape.DIAMOND, CardNumber.NINE), Card(Shape.CLOVER, CardNumber.EIGHT))
        val testWallet = Wallet(cards)
        val testPlayer = Player("제이", testWallet)

        // when
        val resultWallet = testPlayer.draw(testWallet)

        // then
        Assertions.assertThat(resultWallet.cards.size).isEqualTo(testWallet.cards.size + 1)
    }

    @Test
    fun `1장을 드로우 하면 player가 가진 wallet에 카드 총 합이 늘어난다`() {
        // given
        val cards = listOf<Card>(Card(Shape.DIAMOND, CardNumber.NINE), Card(Shape.CLOVER, CardNumber.EIGHT))
        val testWallet = Wallet(cards)
        val testPlayer = Player("제이", testWallet)

        // when
        val resultWallet = testPlayer.draw(testWallet)

        // then
        Assertions.assertThat(resultWallet.sumUp).isGreaterThan(testWallet.sumUp)
    }

    @Test
    fun `플레이어의 총 합이 21을 넘으면 카드를 추가하지 않는다`() {
        // given
        val cards = listOf<Card>(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.KING), Card(Shape.CLOVER, CardNumber.QUEEN))
        val testWallet = Wallet(cards)
        val testPlayer = Player("제이", testWallet)

        // when
        val result = testPlayer.chooseDrawing(testWallet)

        // then
        Assertions.assertThat(testPlayer.wallet).isEqualTo(result)
    }
}
