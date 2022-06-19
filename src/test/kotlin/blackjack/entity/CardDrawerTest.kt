package blackjack.entity

import blackjack.entity.CardNumber.Companion.MAXIMUM_CARD_NUMBER
import blackjack.entity.CardNumber.Companion.MINIMUM_CARD_NUMBER
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CardDrawerTest {

    @Test
    fun `drawSingleCard의 결과 카드를 한 장 생성한다`() {
        // given
        val expectedCard = Card(Shape.CLOVER, CardNumber.NINE)

        // when
        val resultCards = CardDrawer.drawSingleCard()

        // then
        Assertions.assertThat(resultCards).hasSameClassAs(expectedCard)
    }

    @Test
    fun `drawInitialCards의 결과 카드가 생성된다`() {
        // given
        val expectedCard = Card(Shape.CLOVER, CardNumber.NINE)

        // when
        val resultWallet = CardDrawer.drawInitialCards()

        // then
        Assertions.assertThat(resultWallet.cards[0]).hasSameClassAs(expectedCard)
    }

    @Test
    fun `drawInitialCards의 결과 카드가 두 장 생성한다`() {
        // when
        val resultWallet = CardDrawer.drawInitialCards()

        // then
        Assertions.assertThat(resultWallet.cards.size).isEqualTo(2)
    }

    @Test
    fun `drawAdditionalCard 결과 카드 갯수가 하나 늘어난다`() {
        // given
        val cards = listOf<Card>(Card(Shape.CLOVER, CardNumber.NINE), Card(Shape.DIAMOND, CardNumber.EIGHT))
        val cardsSize = cards.size

        // when
        val resultWallet = CardDrawer.drawAdditionalCard(cards)

        // then
        Assertions.assertThat(resultWallet.cards.size).isEqualTo(cardsSize + 1)
    }

    @Test
    fun `getRandomShape은 Shape중 하나를 리턴한다`() {
        // given
        val expected = Shape.CLOVER

        // when
        val result = CardDrawer.getRandomShape()

        // then
        Assertions.assertThat(result).hasSameClassAs(expected)
    }

    @Test
    fun `randomNumer는 1에서 14 중 하나의 숫자를 리턴한다`() {
        // when
        val result = CardDrawer.getRandomNumber().value

        // then
        Assertions.assertThat(result).isGreaterThanOrEqualTo(MINIMUM_CARD_NUMBER)
        Assertions.assertThat(result).isLessThanOrEqualTo(MAXIMUM_CARD_NUMBER)
    }
}
