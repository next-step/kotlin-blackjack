package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ValueSource(strings = ["test1", "test2"])
    @ParameterizedTest
    fun `player는 이름을 가지고 있다`(name: String) {
        val player = Player(name)
        assertEquals(player.name, name)
    }

    @ValueSource(strings = ["HIT", "STAND"])
    @ParameterizedTest
    fun `player는 hit또는 stand 상태를 가질 수 있다`(str: String) {
        val player = Player("test")
        player.status = Status.valueOf(str)
        assertEquals(player.status, Status.valueOf(str))
    }

    @Test
    fun `player가 카드를 받으면 받은 카드 목록에 추가된다`() {
        val player = Player("test")
        val card = Card(Denomination.TWO, Suit.CLUBS)
        player.receiveCard(card)
        assertEquals(player.cards.toString(), card.toString())
    }

    @Test
    fun `player가 받은 카드가 21점을 넘으면 카드를 받을 수 없는 상태가 된다`() {
        val cardDraw = CardDraw.init()
        val player = Player("test")
        val cards = Cards(
            mutableListOf(
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.JACK, Suit.HEARTS),
                Card(Denomination.ACE, Suit.HEARTS)
            )
        )
        player.cards = cards
        player.receiveCard(cardDraw.draw())
        player.status.shouldBe(Status.STAND)
    }
}
