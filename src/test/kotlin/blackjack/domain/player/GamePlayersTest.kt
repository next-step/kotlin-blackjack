package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.cards
import blackjack.domain.score.ScoreState
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class GamePlayersTest : StringSpec({
    "플레이어가 카드를 뽑는다." {
        // given
        val cards = cards(Card(CardNumber.ACE, CardSymbol.SPADE))
        val deck = CardDeck(mutableListOf(Card(CardNumber.TWO, CardSymbol.SPADE)))
        val player = GamePlayer("tris", cards)

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
        val player = GamePlayer("tris", cards)

        // when
        val result = player.isEligibleToHit()

        // then
        result shouldBe true
    }

    "플레이어가 hit 불가능한 상태" {
        // given
        val cards = cards(Card(CardNumber.ACE, CardSymbol.SPADE), Card(CardNumber.TEN, CardSymbol.HEART))
        val player = GamePlayer("tris", cards)

        // when
        val result = player.isEligibleToHit()

        // then
        result shouldBe false
    }

    "플레이어의 상태를 얻는다" {
        forAll(
            row(
                cards(Card(CardNumber.ACE, CardSymbol.SPADE), Card(CardNumber.TEN, CardSymbol.HEART)),
                ScoreState.BLACK_JACK
            ),
            row(
                cards(
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                    Card(CardNumber.TWO, CardSymbol.CLUB)
                ),
                ScoreState.BUST
            ),
            row(
                cards(Card(CardNumber.TEN, CardSymbol.SPADE), Card(CardNumber.NINE, CardSymbol.SPADE)),
                ScoreState.NORMAL
            )
        ) { cards, expect ->
            // given
            val player = GamePlayer("tris", cards)

            // when
            val result = player.scoreState

            // then
            result shouldBe expect
        }
    }
})
