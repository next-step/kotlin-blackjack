package blackjack.domain.player

import blackjack.domain.Deck
import blackjack.domain.Score
import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PlayerCardsTest : StringSpec({

    "카드를 한 장 추가할 수 있다." {
        // given
        val deck = DeckHelper.createMockDeck()
        val card = deck.draw()
        val playerCards = PlayerCards()

        // when
        playerCards.add(card)

        // then
        playerCards.values.size shouldBe 1
        playerCards.values shouldBe listOf(card)
    }

    "카드의 총 점수를 계산한다." {
        // given
        val playerCards = PlayerCards()
        val deck = DeckHelper.createMockDeck()
        addCards(5, playerCards, deck)

        // when
        val score = playerCards.calculateScore()

        // then
        score shouldBe Score(15)
    }

    "카드에 에이스가 있으면, 에이스의 점수 규칙에 따라 블랙잭에 가까울 수 있도록 점수를 계산한다." {
        // given
        val playerCards = PlayerCards()
        val deck = DeckHelper.createMockDeck()
        addCards(2, playerCards, deck)

        // when
        val score = playerCards.calculateScore()

        // then
        score shouldBe Score(13)
    }

    "카드가 버스트라면 true, 아니면 false를 반환한다" {
        forAll(
            row(10, true),
            row(2, false)
        ) { count, expected ->
            // given
            val playerCards = PlayerCards()
            val deck = DeckHelper.createMockDeck()
            addCards(count, playerCards, deck)

            // when
            val result = playerCards.isBust()

            // then
            result shouldBe expected
        }
    }
})

private fun addCards(count: Int, playerCards: PlayerCards, deck: Deck) {
    repeat(count) {
        val card = deck.draw()
        playerCards.add(card)
    }
}
