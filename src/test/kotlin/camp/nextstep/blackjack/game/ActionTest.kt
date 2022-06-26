package camp.nextstep.blackjack.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ActionTest {

    @DisplayName("플레이어는 HIT 혹은 STAY 액션을 취할 수 있다.")
    @Test
    fun gamblerActions() {
        assertThat(Action.HIT).isNotNull
        assertThat(Action.STAY).isNotNull
    }
}
