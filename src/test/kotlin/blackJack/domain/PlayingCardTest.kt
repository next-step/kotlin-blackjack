package blackJack.domain

import blackJack.domain.card.Card
import blackJack.domain.card.PlayingCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardTest {

    @Test
    fun `뽑은 카드가 카드 인스턴스인지 확인`() {
        // given
        val playingCard = PlayingCard.create()

        // when
        val drawCard = playingCard.drawCard()

        // then
        assertThat(drawCard).isInstanceOf(Card::class.java)
    }
}
