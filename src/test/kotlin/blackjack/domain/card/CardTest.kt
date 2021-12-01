package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("카드(Card)")
internal class CardTest {

    @Test
    fun `카드는 슈트와 끗수로 이루어진다`() {
        val playingCard = Card(Suit.CLUB, Denomination.ACE)

        assertAll(
            { assertThat(playingCard).isNotNull },
            { assertThat(playingCard).isExactlyInstanceOf(Card::class.java) },
        )
    }

    @Test
    fun `카드는 점수를 반환한다`() {
        val playingCard = Card(Suit.CLUB, Denomination.ACE)

        assertThat(playingCard.score()).isEqualTo(Score.from(1))
    }
}
