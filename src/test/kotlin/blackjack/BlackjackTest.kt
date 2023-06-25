package blackjack

import blackjack.domain.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackTest {

    @Test
    fun `카드는 4가지 타입을 가진다`() {
        val cardType = CardType.values()
        cardType.size shouldBe 4
    }

    @Test
    fun `카드의 숫자는 13종류이다`() {
        val numberType = NumberType.values()
        numberType.size shouldBe 13
    }

    @Test
    fun `플레이어는 카드를 받는다`() {
        val card = CardDeck().getRandomCard(1)
        val player = Player(card)

        player.cards.size shouldBe 1
    }

    @Test
    fun `게임 시작 시 두장의 카드를 받는다`() {
        val card = CardDeck()
        val player = Player()
        val game = BlackjackGame(listOf(player), card)
        game.initPlayer()
        game.player[0].cards.size shouldBe 2
    }
}