package blackJack.card.deck

import card.CardPack
import card.CardRank
import card.PlayingCard
import card.Suit
import card.deck.Hands
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandsTest {

    @Test
    fun `플레이어 카드 추가`() {
        val hands = Hands.create()

        hands.addCard(CardPack.cards[0])

        val actual = hands.cardList[0]

        assertThat(actual).isEqualTo(PlayingCard(suit = Suit.SPADE, cardRank = CardRank.ACE))
    }

    @Test
    fun `카드 점수 반환(ACE == 1)`() {
        // given : 플레이어 덱을 생성하고 카드를 추가한다.
        val hands = Hands()
        hands.addCard(PlayingCard(Suit.DIAMOND, CardRank.ACE))
        hands.addCard(PlayingCard(Suit.DIAMOND, CardRank.TEN))
        hands.addCard(PlayingCard(Suit.DIAMOND, CardRank.TEN))

        // when : 점수 계산 요청
        val actual: Int = hands.getResultPoint()

        // then :
        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `카드 점수 반환(ACE == 11)`() {
        // given : 플레이어 덱을 생성하고 카드를 추가한다.
        val hands = Hands()
        hands.addCard(PlayingCard(Suit.DIAMOND, CardRank.ACE))
        hands.addCard(PlayingCard(Suit.DIAMOND, CardRank.TEN))

        // when : 점수 계산 요청
        val actual: Int = hands.getResultPoint()

        // then :
        assertThat(actual).isEqualTo(21)
    }
}
