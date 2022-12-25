package blackjack.domain.player

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import blackjack.domain.player.state.Name
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `플레이어들 - 플레이어들 생성 테스트`() {
        // given, when
        val cards = PlayingCards(PlayingCard(Suit.CLUBS, Denomination.ACE), PlayingCard(Suit.CLUBS, Denomination.JACK))
        val player1 = ParticipantFactory.create(Name("pobi"), cards)
        val player2 = ParticipantFactory.create(Name("jason"), cards)
        val actual = Participants(player1, player2)
        val expected = Participants(player1, player2)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어들 - 플레이어들 생성 실패 예외처리 테스트, 플레이어가 2명 미만인 경우`() {
        // given
        val cards = PlayingCards(PlayingCard(Suit.CLUBS, Denomination.ACE), PlayingCard(Suit.CLUBS, Denomination.JACK))
        val player = ParticipantFactory.create(Name("pobi"), cards)

        // when, then
        assertThatThrownBy { Participants(player) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("플레이어는 2명 이상이어야 합니다.")
    }

    @Test
    fun `플레이어들 - 플레이어들 생성 실패 예외처리 테스트, 플레이어가 중복되는 경우`() {
        // given
        val cards = PlayingCards(PlayingCard(Suit.CLUBS, Denomination.ACE), PlayingCard(Suit.CLUBS, Denomination.JACK))
        val players = listOf(ParticipantFactory.create(Name("jason"), cards), ParticipantFactory.create(Name("jason"), cards))

        // when, then
        assertThatThrownBy { Participants(players) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("플레이어의 이름은 중복될 수 없습니다.")
    }
}
