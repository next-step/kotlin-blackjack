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

    @Test
    fun `player가 카드를 받으면 받은 카드 목록에 추가된다`() {
        val player = Player("test")
        val card = Card(Denomination.TWO, Suit.CLUBS)
        player.hit(card)

        player.cards.cards.size shouldBe 1
    }

    @Test
    fun `player가 받은 카드가 21점을 넘으면 버스트 상태가 된다`() {
        val player = Player("test")

        val cards = Cards(
            mutableListOf(
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.JACK, Suit.HEARTS),
                Card(Denomination.JACK, Suit.HEARTS)
            )
        )

        for (card in cards.cards) {
            player.hit(card)
        }

        player.isBusted shouldBe true
    }

    @Test
    fun `player가 받은 카드에 대한 점수를 계산할 수 있다`() {
        val player = Player("test")

        val cards = Cards(
            mutableListOf(
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.JACK, Suit.HEARTS),
                Card(Denomination.ACE, Suit.HEARTS)
            )
        )
        for (card in cards.cards) {
            player.hit(card)
        }

        player.getScore() shouldBe 21
    }
}
