package blackjack.setup

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameStartSettingTest {

    @Test
    fun `참여하는 플레이어의 수만큼 플레이어 리스트가 생성되는지 확인합니다`() {
        val players = listOf("kim", "jo")
        assertThat(GameStartSetting.setGame(players).size).isEqualTo(players.size)
    }

    @Test
    fun `참여하는 플레이어에게 각각 2장의 카드씩 분배되는지 확인합니다`() {
        val players = listOf("kim", "jo")
        GameStartSetting.setGame(players).forEach { assertThat(it.cards.size).isEqualTo(2) }
    }
}
