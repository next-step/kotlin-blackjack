package blackJack.card

import card.CardDeck
import card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `Cards를 생성하고, 카드 반환 요청을 했을 떄, 카드 반환과 카드 인덱스가 증가한다`() {
        // given : Cards를 생성한다.
        val cards = Cards(CardDeck.cards)

        // when : 카드 한장 반환을 요청한다.
        val actual = cards.getCard()

        // then : 첫번째 카드를 반환하고 카드 인덱스가 1 증가한다.
        assertThat(actual).isEqualTo(CardDeck.cards[0])

        // when : 카드 한장 반환을 요청한다.
        val actual2 = cards.getCard()

        // then : 두번째 카드를 반환하고 카드 인덱스가 1 증가한다.
        assertThat(actual2).isEqualTo(CardDeck.cards[1])
    }
}
