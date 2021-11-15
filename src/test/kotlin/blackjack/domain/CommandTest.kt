package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("커멘더(Command)")
internal class CommandTest {

    @Test
    fun `y or n 입력시 알맞는 열거형 반환 테스트`() {
        assertAll(
            { assertThat(Command.values("y")).isEqualTo(Command.YES) },
            { assertThat(Command.values("Y")).isEqualTo(Command.YES) },
            { assertThat(Command.values("n")).isEqualTo(Command.NO) },
            { assertThat(Command.values("N")).isEqualTo(Command.NO) }
        )
    }
}
