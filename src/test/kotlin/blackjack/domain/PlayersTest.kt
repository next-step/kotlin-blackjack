package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.player.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "플레이어가 카드를 뽑는다." {
        // given
        val cards = cards(Card(CardNumber.ACE, CardSymbol.SPADE))
        val deck = CardDeck(mutableListOf(Card(CardNumber.TWO, CardSymbol.SPADE)))
        val player = Player("tris", cards)

        // when
        player.draw(deck)

        // then
        player.cards shouldBe cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.TWO, CardSymbol.SPADE),
        )
    }
    "플레이어가 hit 가능한 상태" {
        // given
        val cards = cards(Card(CardNumber.ACE, CardSymbol.SPADE))
        val player = Player("tris", cards)

        // when
        val result = player.isEligibleToHit()

        // then
        result shouldBe true
    }

    "플레이어가 hit 불가능한 상태" {
        // given
        val cards = cards(Card(CardNumber.ACE, CardSymbol.SPADE), Card(CardNumber.TEN, CardSymbol.HEART))
        val player = Player("tris", cards)

        // when
        val result = player.isEligibleToHit()

        // then
        result shouldBe false
    }
})
