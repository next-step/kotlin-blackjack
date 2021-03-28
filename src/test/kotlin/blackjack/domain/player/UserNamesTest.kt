package blackjack.domain.player

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class UserNamesTest {
    @DisplayName("중복된 이름의 플레이어가 있는 경우 예외 발생")
    @Test
    fun validateDuplicatedName() {
        val names = listOf("pobi", "pobi")
        assertThrows<IllegalArgumentException> { UserNames(names) }
    }
}
