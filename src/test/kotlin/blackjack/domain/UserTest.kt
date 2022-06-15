package blackjack.domain

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
            User(source, listOf())
        }
    }
}
