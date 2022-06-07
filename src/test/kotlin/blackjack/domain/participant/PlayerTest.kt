package blackjack.domain.participant

import blackjack.domain.CLUB_KING
import blackjack.domain.DIAMOND_ACE
import blackjack.domain.HEART_SIX
import blackjack.domain.HEART_TWO
import blackjack.domain.SPADE_FIVE
import blackjack.domain.SPADE_TEN
import blackjack.domain.card.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PlayerTest {

    @ParameterizedTest
    @MethodSource("패의 합계가 21초과인 케이스")
    fun `플레이어는 패의 합계가 21을 초과하면 BUST 된다`(cards: Array<Card>) {
        // given
        val player = Player("pug")

        // when
        player.addCards(*cards)

        // then
        assertThat(player.status).isEqualTo(ParticipantStatus.BUST)
    }

    @ParameterizedTest
    @MethodSource("패의 합계가 21이하인 케이스")
    fun `플레이어는 카드의 점수가 21을 초과하지 않으면 BUST 되지 않는다`(cards: Array<Card>) {
        // given
        val player = Player("pug")

        // when
        player.addCards(*cards)

        // then
        assertThat(player.status).isNotEqualTo(ParticipantStatus.BUST)
    }

    companion object {
        @JvmStatic
        fun `패의 합계가 21초과인 케이스`() = Stream.of(
            Arguments.of(arrayOf(SPADE_TEN, CLUB_KING, DIAMOND_ACE, HEART_TWO)),
            Arguments.of(arrayOf(SPADE_TEN, CLUB_KING, SPADE_FIVE)),
        )

        @JvmStatic
        fun `패의 합계가 21이하인 케이스`() = Stream.of(
            Arguments.of(arrayOf(SPADE_TEN, CLUB_KING)),
            Arguments.of(arrayOf(SPADE_TEN, SPADE_FIVE, HEART_SIX)),
            Arguments.of(arrayOf(CLUB_KING, SPADE_FIVE, HEART_TWO, DIAMOND_ACE)),
            Arguments.of(arrayOf(DIAMOND_ACE))
        )
    }
}
