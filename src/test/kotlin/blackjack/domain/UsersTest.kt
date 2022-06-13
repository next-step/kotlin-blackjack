package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Created by Jaesungchi on 2022.06.07..
 */
class UsersTest {
    @Test
    fun `유저가 한명도 없을경우 IllegalArgumentException을 던진다`() {
        assertThrows<IllegalArgumentException> {
            Users(listOf())
        }
    }
}
