package blackjack.domain.player

import blackjack.error.CommandNotFoundException
import blackjack.ui.Command
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(strings = ["", "a", "10", "$"])
    fun `y or n 외의 다른 문자열 입력시 예외발생`(command: String) {
        val exception = assertThrows<CommandNotFoundException> { Command.values(command) }

        assertThat(exception.message).isEqualTo("'%s'는 유효한 커멘드가 아닙니다.".format(command))
    }
}
