package blackjack.domain

import fixtures.createCard
import fixtures.createCards
import io.kotest.assertions.assertSoftly
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    private val cards: Cards = createCards(
        createCard(suit = Suit.CLUBS, denomination = Denomination.TWO),
        createCard(suit = Suit.HEARTS, denomination = Denomination.TEN),
        createCard(suit = Suit.SPADES, denomination = Denomination.ACE),
    )

    @Test
    fun `cards에 card를 더하면 새로운 리스트 프로퍼티로 가진 Cards를 생성한다`() {
        // given
        val card = createCard(suit = Suit.DIAMONDS, denomination = Denomination.TWO)

        // when
        val newCards = cards + card

        // then
        assertSoftly {
            assertThat(newCards.size).isEqualTo(4)
            assertThat(newCards).isNotSameAs(cards)
        }
    }

    @Test
    fun `cards 문자열 변환 테스트`() {
        // given
        // when
        val cardsString = cards.toString()

        // then
        assertThat(cardsString).isEqualTo("2클로버,  10하트,  A스페이드")
    }
}
