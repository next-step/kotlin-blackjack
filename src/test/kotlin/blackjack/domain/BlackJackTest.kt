package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BlackJackTest {
    @Test
    internal fun `게임이 시작되면 플레이어에게 두장의 카드가 주어진다`() {
        val player = Player("pobi")
        player.cards.value.size shouldBe 0
        BlackJack(listOf(player)).start()
        player.cards.value.size shouldBe 2
    }

    @Test
    internal fun `플레이어가 21점이 초과하지않으면 턴은 계속된다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.A)
            )
        )
        val player = Player("pobi", cards)
        val game = BlackJack(listOf(player))

        player.score() shouldBe 21
        game.isEnd() shouldBe false
    }

    @Test
    internal fun `플레이어가 21점을 초과하면 더이상 카드를 뽑을 수 없어 턴이 종료된다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.NINE),
                Card(Shape.CLOVER, Character.FIVE),
                Card(Shape.CLOVER, Character.TEN)
            )
        )
        val player = Player("pobi", cards)
        val game = BlackJack(listOf(player))

        player.score() shouldBe 24
        game.isEnd() shouldBe true
    }

    @Test
    internal fun `플레이어가 y를 대답하면 턴은 계속된다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.TWO)
            )
        )
        val player = Player("pobi", cards)
        val game = BlackJack(listOf(player))
        game.playGameTurn("y")
        game.isEnd() shouldBe false
    }

    @Test
    internal fun `플레이어가 n를 대답하면 턴이 종료된다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.TWO)
            )
        )
        val player = Player("pobi", cards)
        val game = BlackJack(listOf(player))
        game.playGameTurn("n")
        game.isEnd() shouldBe true
    }

    @Test
    internal fun `이전 플레이어가 끝나면 게임차례는 넘어간다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.TWO)
            )
        )
        val player1 = Player("pobi", cards)
        var player2 = Player("ryan", cards)
        val game = BlackJack(listOf(player1, player2))

        game.getNowPlayer() shouldBe player1
        game.playGameTurn("n")
        game.getNowPlayer() shouldBe player2
        game.isEnd() shouldBe false
    }
}
