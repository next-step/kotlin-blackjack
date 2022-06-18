package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Created by Jaesungchi on 2022.06.10..
 */
class CardsTest {
    @Test
    fun `카드 추가시 추가가 잘 된다`() {
        val cards = Cards().apply { addCard(Deck().takeCard()) }
        assertThat(cards.getSize()).isEqualTo(1)
    }

    @Test
    fun `A스페이드와 8하트 카드를 갖고 있는경우 점수는 19점이다`() {
        val cards = Cards().apply {
            addCard(ACE(CardType.SPADE))
            addCard(EIGHT(CardType.HEART))
        }
        assertThat(cards.getScore().value).isEqualTo(19)
    }

    @Test
    fun `A다이아와 K하트 카드를 갖고 있는경우 점수는 21점이다`() {
        val cards = Cards().apply {
            addCard(ACE(CardType.SPADE))
            addCard(KING(CardType.HEART))
        }
        assertThat(cards.getScore().value).isEqualTo(21)
    }

    @Test
    fun `A다이아와 A클로버 카드를 갖고 있는경우 점수는 12점이다`() {
        val cards = Cards().apply {
            addCard(ACE(CardType.SPADE))
            addCard(ACE(CardType.CLUB))
        }
        assertThat(cards.getScore().value).isEqualTo(12)
    }

    @Test
    fun `A다이아와 A클로버 A하트 카드를 갖고 있는경우 점수는 13점이다`() {
        val cards = Cards().apply {
            addCard(ACE(CardType.SPADE))
            addCard(ACE(CardType.CLUB))
            addCard(ACE(CardType.HEART))
        }
        assertThat(cards.getScore().value).isEqualTo(13)
    }
}
