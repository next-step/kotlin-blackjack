package blackJack.card.deck

import card.CardPack
import card.CardRank
import card.PlayingCard
import card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameDeckTest {

    @Test
    fun `GameDeck을 생성하고, 카드 반환 요청을 했을 떄, 카드 반환과 카드 인덱스가 증가한다`() {
        // given : Cards를 생성한다.

        val gameDeck = FakeGameDeck(CardPack.cards.toMutableList())

        // when : 카드 한장 반환을 요청한다.
        val actual = gameDeck.getCardWithIncrease()

        // then : 첫번째 카드를 반환하고 카드 인덱스가 1 증가한다.
        assertThat(actual).isEqualTo(PlayingCard(Suit.SPADE, CardRank.ACE))

        // when : 카드 한장 반환을 요청한다.
        val actual2 = gameDeck.getCardWithIncrease()

        // then : 두번째 카드를 반환하고 카드 인덱스가 1 증가한다.
        assertThat(actual2).isEqualTo(PlayingCard(Suit.HEART, CardRank.ACE))
    }
}
