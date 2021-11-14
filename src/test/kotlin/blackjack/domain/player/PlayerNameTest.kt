package blackjack.domain.player

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

@Suppress("NonAsciiCharacters")
class PlayerNameTest {

    @ParameterizedTest
    @EmptySource
    fun `PlayerName은 공란일 수 없다`(name: String) {
        assertThrows<IllegalArgumentException> {
            PlayerName(name)
        }
    }
}
