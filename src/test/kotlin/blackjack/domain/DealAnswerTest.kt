package blackjack.domain

import blackjack.view.InputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealAnswerTest {
    @Test
    fun `카드 수락 여부`() {
        val yes = InputView.selectDealAnswer("y")
        assertThat(yes.isYes()).isTrue()
    }
}
