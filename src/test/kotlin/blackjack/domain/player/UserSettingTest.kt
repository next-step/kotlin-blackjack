package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UserSettingTest {

    @Test
    fun `UserSetting 은 이름과 배팅 금액을 갖는다`() {
        val userSetting = UserSetting("김성주", 1_000)

        assertAll("프로퍼티 값 확인", {
            assertThat(userSetting.name).isEqualTo("김성주")
            assertThat(userSetting.bettingMoney).isEqualTo(1_000)
        })
    }
}
