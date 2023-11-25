package blackJack

import card.CardNumber
import card.PlayingCard
import card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardTest {

    @Test
    fun `트럼프 카드를 생성하고, 해당 카드의 포인트를 요청할 때, 포인트를 반환한다`() {
        // given : 트럼프 카드를 생성한다.
        val playingCard = PlayingCard(Suit.SPADE, CardNumber.TREE)
        val playingCard2 = PlayingCard(Suit.HEART, CardNumber.TWO)
        val playingCard3 = PlayingCard(Suit.HEART, CardNumber.ACE)
        val playingCard4 = PlayingCard(Suit.HEART, CardNumber.KING)

        // when : 해당 카드의 포인트를 요청한다.
        val actual = playingCard.getPoint()
        val actual2 = playingCard2.getPoint()
        val actual3 = playingCard3.getPoint()
        val actual4 = playingCard3.getPoint(true)
        val actual5 = playingCard4.getPoint()

        // then : 카드별 포인트를 반환한다
        assertThat(actual).isEqualTo(3)
        assertThat(actual2).isEqualTo(2)
        assertThat(actual3).isEqualTo(1)
        assertThat(actual4).isEqualTo(11)
        assertThat(actual5).isEqualTo(10)
    }

    @Test
    fun `트럼프 카드를 만들고, 무늬 명칭을 요청할때, 무늬 명을 반환한다`() {
        // given : 카드를 만들고
        val playingCard = PlayingCard(Suit.SPADE, CardNumber.TREE)

        // when : 무늬 명칭을 요청하면
        val actual = playingCard.getSuitName()

        // then : 무늬 명을 반환한다.
        assertThat(actual).isEqualTo("스페이드")
    }
}
