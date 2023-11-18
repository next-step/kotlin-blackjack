package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `쉼표를 기준으로 Player 들이 구분 되어 리스트로 반환된다`() {
        val inputNames = "pobi, jason"
        val names = Player.splitNames(inputNames)
        assertThat(names).containsExactly("pobi", "jason")
    }

    @Test
    fun `Player 이름을 입력하면 정상적으로 배팅이 된다`() {
        val name = "pobi"
        val player = Player.initBetting(name)
        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards.cards.size).isEqualTo(2)
    }
}
