package blackjack.domain

import blackjack.domain.state.Stand
import blackjack.domain.state.Started
import fixtures.createBlackjackCards
import fixtures.createCard
import fixtures.createCards
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class ParticipantTest {
    private val cards = createCards(
        createCard(),
        createCard()
    )

    @Test
    fun `Hit이 아닌 상태를 가진 참여자는 카드를 받을 수 없다`() {
        // given
        val participant = Participant(
            Stand(cards)
        )
        // when
        val canReceive = participant.canReceiveOneMoreCard()
        // then
        assertThat(canReceive).isFalse()
    }

    @Test
    fun `처음 카드를 받을 때 2(INITIAL_DEAL_SIZE)장 이상의 카드를 받을 수 없다`() {
        // given
        val participant = Participant()
        // when
        assertThatIllegalArgumentException().isThrownBy {
            participant.receiveInitialCards(
                cards = createCards(
                    createCard(),
                    createCard(),
                )
            )
        }.withMessage("처음 받아야 할 카드 수는 ${Deck.INITIAL_DEAL_SIZE}장 입니다.")
    }

    @Test
    fun `참여자는 Stand로 상태를 변경할 수 있다`() {
        // given
        val participant = Participant()
        // when
        participant.turnStand()
        // then
        assertThat(participant.state).isInstanceOf(Stand::class.java)
    }

    @Test
    fun `참여자 상태가 Stand일 경우 isStand는 true를 반환한다`() {
        // given
        val participant = Participant(Stand(cards))
        // when
        val isStand = participant.isStand()
        // then
        assertThat(isStand).isTrue()
    }

    @Test
    fun `getScore 테스트`() {
        // given
        val participant = Participant(Started(createBlackjackCards()))
        // when
        val score = participant.getScore()
        // then
        assertThat(score.value).isEqualTo(Score.BLACKJACK)
    }
}
