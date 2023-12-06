package blackJack.card

import card.CardRank
import card.PlayingCard
import card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardTest {

    @Test
    fun `트럼프 카드를 생성하고, 해당 카드의 포인트를 요청할 때, 포인트를 반환한다`() {
        // given : 트럼프 카드를 생성한다.
        val playingCard = PlayingCard(Suit.SPADE, CardRank.TREE)

        // when : 해당 카드의 포인트를 요청한다.
        val actual = playingCard.getPoint()

        // then : 카드별 포인트를 반환한다
        assertThat(actual).isEqualTo(3)
    }

    @Test
    fun `트럼프 카드를 만들고, 무늬 명칭을 요청할때, 무늬 명을 반환한다`() {
        // given : 카드를 만들고
        val playingCard = PlayingCard(Suit.SPADE, CardRank.TREE)

        // when : 무늬 명칭을 요청하면
        val actual = playingCard.getSuitName()

        // then : 무늬 명을 반환한다.
        assertThat(actual).isEqualTo("스페이드")
    }
}
