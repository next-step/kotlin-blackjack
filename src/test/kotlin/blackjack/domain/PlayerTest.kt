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

    @Test
    fun `보유한 카드의 점수 합이 블랙잭 기준치(21) 미만이면 추가로 카드를 받을 수 있는 상태이다`() {
        val cards: MutableList<Card> = mutableListOf(
            Card(Suit.HEART, Rank.TEN),
            Card(Suit.CLUB, Rank.TEN)
        )
        val hand: Hand = Hand(cards)
        val player: Player = Player("test", hand)
        player.updateState()
        assertEquals(PlayerState.UNDER, player.state)
    }

    @Test
    fun `만들 수 있는 최종 점수가 모두 블랙잭 기준치(21)를 넘는다면 버스트이다`() {
        val cards: MutableList<Card> = mutableListOf(
            Card(Suit.HEART, Rank.TEN),
            Card(Suit.CLUB, Rank.TEN),
            Card(Suit.CLUB, Rank.TWO),
        )
        val hand: Hand = Hand(cards)
        val player: Player = Player("test", hand)
        player.updateState()
        assertEquals(PlayerState.BUST, player.state)
    }

    @Test
    fun `최종 점수가 블랙잭 기준치(21)이고 가진 카드가 2장이면 블랙잭이다`() {
        val cards: MutableList<Card> = mutableListOf(
            Card(Suit.HEART, Rank.TEN),
            Card(Suit.CLUB, Rank.ACE),
        )
        val hand: Hand = Hand(cards)
        val player: Player = Player("test", hand)
        player.updateState()
        assertEquals(PlayerState.BLACKJACK, player.state)
    }
}
