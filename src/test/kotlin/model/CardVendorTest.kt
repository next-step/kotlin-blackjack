package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardVendorTest {
    @Test
    fun `게임 시작 시 카드 두 장을 나눠준다`() {
        val player = Player("Jake")
        val deck = Deck()
        CardVendor(deck).dealOut(player)
        assertThat(player.cards.size).isEqualTo(2)
    }
}
