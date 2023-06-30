package blackjack

import domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["peter", "승현"])
    fun `Person 객체에 name 지정 테스트`(name: String) {
        assertThat(Player(name).name).isEqualTo(name)
    }
}
