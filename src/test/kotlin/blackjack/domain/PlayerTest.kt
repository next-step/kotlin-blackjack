package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["a,a", "a,b,a", "a,b,b"])
    fun `플레이어는 유일한 이름을 가진다`(namesInput: String) {
        val names = namesInput.split(",").toTypedArray()
        assertThrows<IllegalArgumentException> { Players(*names) }
    }

    @Test
    fun `플레이어는 최초에 카드 2장을 분배 받는다`() {
        val player: Player = Player("test")
        val deck: Deck = Deck.getDeck()
        player.init(deck)
        assertEquals(Rule.INIT_CARD_COUNT, player.hand.getCardCount())
    }

    @Test
    fun `카드 분배는 최초 한 번만 이루어진다`() {
        val player: Player = Player("test")
        val deck: Deck = Deck.getDeck()
        player.init(deck)
        assertThrows<IllegalStateException> { player.init(deck) }
    }
}
