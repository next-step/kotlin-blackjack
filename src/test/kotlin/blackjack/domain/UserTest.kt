package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

/**
 * Created by Jaesungchi on 2022.06.07..
 */
class UserTest {
    @ParameterizedTest
    @EmptySource
    fun `이름이 빈칸일 경우 IllegalArgumentException을 던진다`(source: String) {
        assertThrows<IllegalArgumentException> {
            User(source)
        }
    }

    @Test
    internal fun `게임 시작시 카드를 2장씩 받는다`() {
        assertThat(User("name").cards.cards).hasSize(2)
    }
}
