package blackjack.view

import blackjack.domain.player.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

/**
 * Created by Jaesungchi on 2022.06.07..
 */
class InputViewTest {
    @ParameterizedTest
    @NullAndEmptySource
    fun `게임 참여자 이름이 빈칸이거나 NULL일경우 IllegalArgumentException을 던진다`(source: String?) {
        assertThrows<IllegalArgumentException> {
            InputView.getPlayersName { source }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["asss,dddd,a"])
    internal fun `게임 참여자 이름을 받고 쉼표를 기준으로 잘 나눈다`(source: String) {
        assertThat(InputView.getPlayersName { source }).hasSize(3)
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun `카드 받기 입력이 NULL일경우 IllegalArgumentException을 던진다`(source: String?) {
        assertThrows<IllegalArgumentException> {
            InputView.getYesOrNo { source }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["h", "f", "r", "1"])
    fun `카드 받기 입력이 y나 n이 아닌경우 IllegalArgumentException을 던진다`(source: String?) {
        assertThrows<IllegalArgumentException> {
            InputView.getYesOrNo { source }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["asss,dddd,a"])
    fun `배팅금액에 글자가 들어오는 경우 IllegalArgumentException을 던진다`(source: String?) {
        assertThrows<IllegalArgumentException> {
            InputView.getBatMoney(User("hello", listOf())) { source }
        }
    }
}
