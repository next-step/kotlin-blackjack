package blackjack.view

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource

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
}
