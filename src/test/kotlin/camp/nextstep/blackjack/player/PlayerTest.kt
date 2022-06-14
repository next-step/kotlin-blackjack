package camp.nextstep.blackjack.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class PlayerTest {

    @DisplayName("플레이어는 이름을 가지고 있다.")
    @Test
    fun playerHasName() {
        val tim = Player("tim")
        assertThat(tim.name).isEqualTo("tim")
    }

    @DisplayName("플레이어 이름은 공백일 수 없다.")
    @Test
    fun playerCannotHaveEmptyName() {
        val block = "\n"
        assertThrows<IllegalArgumentException> {
            Player(block)
        }
    }

    @DisplayName("플레이어는 처음에 카드를 가지고 있지 않는다.")
    @Test
    fun newPlayerHasNotCards() {
        val tim = Player("tim")
        assertThat(tim.cards).isEmpty()
    }
}
