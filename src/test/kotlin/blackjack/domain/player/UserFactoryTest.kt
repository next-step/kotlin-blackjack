package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

internal class UserFactoryTest {
    @DisplayName("유저 생성")
    @Test
    fun create() {
        val input = "pobi,jason"

        val players = UserFactory.create(input)

        assertAll(
            { assertThat(players.users.size).isEqualTo(3) },
            { assertThat(players.users.map { it.userName }).containsExactly(UserName("딜러"), UserName("pobi"), UserName("jason")) }
        )
    }

    @DisplayName("중복된 이름의 플레이어가 있는 경우 예외 발생")
    @Test
    fun validateDuplicatedName() {
        val duplicatedInput = "pobi,pobi"
        assertThrows<IllegalArgumentException> { UserFactory.create(duplicatedInput) }
    }
}
