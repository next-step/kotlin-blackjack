package blackjack.domain

import blackjack.domain.state.Stand
import blackjack.domain.state.Started
import fixtures.createBlackjackCards
import fixtures.createCard
import fixtures.createCards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
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
        canReceive shouldBe false
    }

    @Test
    fun `처음 카드를 받을 때 2(INITIAL_DEAL_SIZE)장 이상의 카드를 받을 수 없다`() {
        // given
        val participant = Participant()
        val cards = createCards(
            createCard(),
            createCard(),
            createCard()
        )
        // when
        shouldThrow<IllegalArgumentException> {
            participant.receiveInitialCards(cards)
        }
    }

    @Test
    fun `참여자는 Stand로 상태를 변경할 수 있다`() {
        // given
        val participant = Participant()
        // when
        participant.turnStand()
        // then
        participant.state::class shouldBe Stand::class
    }

    @Test
    fun `참여자 상태가 Stand일 경우 isStand는 true를 반환한다`() {
        // given
        val participant = Participant(Stand(cards))
        // when
        val isStand = participant.isStand()
        // then
        isStand shouldBe true
    }

    @Test
    fun `게임 참여자는 현재 상태에서 점수를 얻을 수 있다`() {
        // given
        val participant = Participant(Started(createBlackjackCards()))
        // when
        val score = participant.getScore()
        // then
        score.value shouldBe Score.BLACKJACK
    }
}
