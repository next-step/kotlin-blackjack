package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Created by Jaesungchi on 2022.06.07..
 */
class UsersTest {

    @ParameterizedTest
    @ValueSource(strings = ["jason,link", "james, link"])
    fun `입력받은 이름들을 쉼표로 잘 나눈다`(source: String) {
        assertThat(Users.of(source).users).hasSize(2)
    }

    @Test
    fun `유저가 한명도 없을경우 IllegalArgumentException을 던진다`() {
        assertThrows<IllegalArgumentException> {
            Users(listOf())
        }
    }
}
