package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.CardDeck
import blackjack.domain.CardType
import blackjack.domain.NumberType
import blackjack.domain.Player
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
        val name = "홍길동"
        val card = CardDeck().getRandomCard(1)
        val player = Player(Cards(card), name = name)

        player.cards.card.size shouldBe 1
    }

    @Test
    fun `게임 시작 시 두장의 카드를 받는다`() {
        val name = "홍길동"
        val card = CardDeck().getRandomCard(2)
        val player = Player(Cards(card), name = name)
        val game = BlackjackGame(listOf(player), CardDeck())
        game.initPlayer()
        game.player[0].cards.card.size shouldBe 2
    }

    @Test
    fun `카드의 합이 21이 되면 블랙잭이다`() {
        val name = "홍길동"
        val card = Cards(
            mutableListOf(
                Card(NumberType.ACE, CardType.DIAMOND),
                Card(NumberType.KING, CardType.SPADE)
            )
        )
        val player = Player(card, name = name)
        player.score shouldBe 21
        player.isBlackJack() shouldBe true
    }

    @Test
    fun `블랙잭을 넘어가는 점수에는 카드를 받을 수 없다`() {
        val name = "홍길동"
        val card = Cards(
            mutableListOf(
                Card(NumberType.QUEEN, CardType.DIAMOND),
                Card(NumberType.KING, CardType.SPADE)
            )
        )
        val player = Player(card, name = name)
        val giveCard = Card(NumberType.NINE, CardType.HEART)
        player.getCard(giveCard)

        player.isGetCardPossible() shouldBe false
    }
}
