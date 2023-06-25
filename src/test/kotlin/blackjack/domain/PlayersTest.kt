package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.player.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "플레이어가 카드를 뽑는다." {
        // given
        val cards = cards(Card(CardNumber.ACE, CardSymbol.SPADE))
        val player = Player("tris", cards)

        // when
        player.draw(Card(CardNumber.TWO, CardSymbol.SPADE))

        // then
        player.cards shouldBe cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.TWO, CardSymbol.SPADE),
        )
    }

    "점수가 버스트 플레이어" {
        val cards = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE), Card(CardNumber.TWO, CardSymbol.HEART),
            Card(CardNumber.JACK, CardSymbol.SPADE), Card(CardNumber.QUEEN, CardSymbol.HEART),
        )
        val player = Player("test", cards)
        player.isBustPlayer() shouldBe true
        player.isNotBustPlayer() shouldBe false
    }
    "점수가 버스트 플레이어가 아닌 경우" {
        val cards = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.JACK, CardSymbol.SPADE),
            Card(CardNumber.QUEEN, CardSymbol.HEART),
        )
        val player = Player("test", cards)
        player.isBustPlayer() shouldBe false
        player.isNotBustPlayer() shouldBe true
    }
    "블랙잭인 경우" {
        val cards = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.JACK, CardSymbol.SPADE),
            Card(CardNumber.QUEEN, CardSymbol.HEART),
        )
        val player = Player("test", cards)
        player.isBlackJack() shouldBe true
        player.isNotBlackJack() shouldBe false
    }
    "블랙잭인 아닌 경우" {
        val cards = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.NINE, CardSymbol.SPADE),
            Card(CardNumber.TEN, CardSymbol.HEART),
        )
        val player = Player("test", cards)
        player.isBlackJack() shouldBe false
        player.isNotBlackJack() shouldBe true
    }
})
