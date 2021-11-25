package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ProfileTest {

    @Test
    fun `프로필을 생성할 수 있다`() {
        val givenName = Name("test1")
        val givenStatus = PlayerStatus.STOP

        assertThat(Profile(givenName, givenStatus)).isNotNull
    }

    @Test
    fun `이름을 입력받아 프로필을 생성할 수 있다`() {
        val givenName = Name("test1")
        assertThat(Profile.from(givenName)).isNotNull
    }

    @Test
    fun `프로필의 상태를 활성화하면 턴의 상태는 true를 리턴한다`() {
        val givenName = Name("test1")
        val givenStatus = PlayerStatus.STOP
        val profile = Profile(givenName, givenStatus)

        val actual = profile.turnOn()

        assertThat(actual.isBurst()).isTrue
    }

    @Test
    fun `프로필의 상태를 비활성하면 턴의 상태는 false를 리턴한다`() {
        val givenName = Name("test1")
        val givenStatus = PlayerStatus.BURST
        val profile = Profile(givenName, givenStatus)

        val actual = profile.turnOff()

        assertThat(actual.isBurst()).isFalse
    }
}
