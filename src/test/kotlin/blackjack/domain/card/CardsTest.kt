package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class CardsTest {

    @Test
    fun `빈 Cards 생성`() {
        val cards = Cards.createEmpty()

        assertThat(cards.cards).isEmpty()
    }

    @Test
    fun `Cards에 카드 추가`() {
        val card = Card(Symbol.ACE, Type.CLUB)
        val cards = Cards.createEmpty()

        cards.add(card)

        assertThat(cards.cards.size).isEqualTo(1)
        assertThat(cards.cards[0]).isEqualTo(card)
    }

    @Test
    fun `점수가 BlackJackScore면서 카드가 2장이면 BlackJack`() {
        val cards = Cards(listOf(
            Card(Symbol.ACE, Type.CLUB),
            Card(Symbol.TEN, Type.CLUB),
        ))

        assertThat(cards.isBlackJack()).isTrue
    }

    @Test
    fun `점수가 BlackJackScore지만 카드가 2장이 아니라면 BlackJack이 아님`() {
        val cards = Cards(listOf(
            Card(Symbol.ACE, Type.CLUB),
            Card(Symbol.TEN, Type.CLUB),
            Card(Symbol.TEN, Type.CLUB),
        ))

        assertThat(cards.isBlackJack()).isFalse
    }

    @Test
    fun `Cards의 Score 계산 - Ace가 없을 경우`() {
        val cards = Cards(listOf(
            Card(Symbol.TWO, Type.CLUB),
            Card(Symbol.THREE, Type.CLUB),
            Card(Symbol.FOUR, Type.CLUB),
        ))

        val result = cards.score

        assertThat(result.value).isEqualTo(9)
    }

    @Test
    fun `Cards의 Score 계산 - Ace 보정을 받지 않는 경우`() {
        val cards = Cards(listOf(
            Card(Symbol.ACE, Type.CLUB),
            Card(Symbol.THREE, Type.CLUB),
            Card(Symbol.FOUR, Type.CLUB),
            Card(Symbol.TEN, Type.CLUB),
        ))

        val result = cards.score

        assertThat(result.value).isEqualTo(18)
    }

    @Test
    fun `Cards의 Score 계산 - Ace 보정을 받는 경우`() {
        val cards = Cards(listOf(
            Card(Symbol.ACE, Type.CLUB),
            Card(Symbol.THREE, Type.CLUB),
            Card(Symbol.FOUR, Type.CLUB),
        ))

        val result = cards.score

        assertThat(result.value).isEqualTo(18)
    }
}
