package blackjack.domain

import blackjack.fixture.CardFixture
import blackjack.fixture.PlayerFixture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.assertj.core.api.ThrowableAssert
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import java.util.LinkedList

class BlackJackMachineTest {

    @Test
    fun `카드덱이 비어있으면 더 이상 카드를 뽑을 수 없다`() {
        // given
        val machine = BlackJackMachine(LinkedList())
        val cards = CardFixture.CARDS_WITH_SUM_18
        val player = PlayerFixture.플레이어_생성(cards)

        // when
        val callable = ThrowableAssert.ThrowingCallable { machine.play(true, player) }

        // then
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy(callable)
            .withMessageMatching("더 이상 카드를 뽑을 수 없습니다.")
    }

    @Test
    fun `플레이어가 더 이상 플레이를 원하지 않는다면 현재 플레이어의 상태를 finished로 만든다`() {
        // given
        val machine = BlackJackMachine()
        val cards = CardFixture.CARDS_WITH_SUM_18
        val player = PlayerFixture.플레이어_생성(cards)

        // when
        val finishedPlayer = machine.play(false, player)

        // then
        assertAll(
            { assertThat(finishedPlayer.finished).isTrue },
            { assertThat(finishedPlayer.cards).isEqualTo(player.cards) },
            { assertThat(finishedPlayer.name).isEqualTo(player.name) }
        )
    }
}
