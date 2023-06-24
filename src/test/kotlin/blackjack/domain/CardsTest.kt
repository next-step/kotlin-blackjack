package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.card.Cards
import blackjack.domain.score.Score
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class CardsTest : StringSpec({
    "중복된 카드가 들어있을 때" {
        shouldThrow<IllegalArgumentException> {
            cards(
                Card(CardNumber.ACE, CardSymbol.SPADE),
                Card(CardNumber.ACE, CardSymbol.SPADE),
            )
        }
            .shouldHaveMessage("중복된 카드를 가질 수 없습니다.")
    }

    "카드를 더하면 카드가 추가된다" {
        val cards = Cards(listOf())
        val card = Card(CardNumber.ACE, CardSymbol.SPADE)
        (cards + card) shouldBe cards(Card(CardNumber.ACE, CardSymbol.SPADE))
    }

    "점수를 구한다" {
        table(
            headers("카드 리스트", "점수"),
            row(cards(Card(CardNumber.ACE, CardSymbol.SPADE)), Score(11)),
            row(cards(Card(CardNumber.ACE, CardSymbol.SPADE), Card(CardNumber.TWO, CardSymbol.SPADE)), Score(13)),
            row(cards(Card(CardNumber.ACE, CardSymbol.SPADE), Card(CardNumber.ACE, CardSymbol.HEART)), Score(12)),
            row(
                cards(
                    Card(CardNumber.ACE, CardSymbol.SPADE), Card(CardNumber.ACE, CardSymbol.HEART),
                    Card(CardNumber.ACE, CardSymbol.DIAMOND), Card(CardNumber.ACE, CardSymbol.CLUB),
                ),
                Score(14)
            ),
            row(
                cards(
                    Card(CardNumber.ACE, CardSymbol.SPADE), Card(CardNumber.JACK, CardSymbol.SPADE),
                    Card(CardNumber.QUEEN, CardSymbol.SPADE), Card(CardNumber.KING, CardSymbol.SPADE),
                ),
                Score(31)
            ),
        ).forAll { cards, expect ->
            cards.getScore() shouldBe expect
        }
    }

    "버스트인지 파악" {
        val cards = cards(
            Card(CardNumber.TWO, CardSymbol.SPADE),
            Card(CardNumber.QUEEN, CardSymbol.SPADE),
            Card(CardNumber.KING, CardSymbol.SPADE),
        )
        cards.isBust() shouldBe true
    }

    "카드가 블랙잭인 경우" {
        val cards = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.QUEEN, CardSymbol.SPADE),
            Card(CardNumber.KING, CardSymbol.SPADE),
        )
        cards.isBlackJack() shouldBe true
    }
})
