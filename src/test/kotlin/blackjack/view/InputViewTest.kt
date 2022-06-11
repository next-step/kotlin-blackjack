package blackjack.view

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
}
