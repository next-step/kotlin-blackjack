package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.type.Ace
import blackjack.domain.card.type.Eight
import blackjack.domain.card.type.Four
import blackjack.domain.card.type.Jack
import blackjack.domain.card.type.Queen
import blackjack.domain.card.type.Seven
import blackjack.domain.card.type.Suit
import blackjack.domain.card.type.Three
import blackjack.domain.card.type.Two
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardsInHandTest : StringSpec({
    "손에 든 카드를 생성할수 있다." {
        shouldNotThrow<Throwable> { CardsInHand(emptyList()) }
    }

    "손에 쥐고 있는 카드의 점수를 계산할수 있다." {
        val cardSet = listOf(
            listOf(
                Card(Two(), Suit.CLUB),
                Card(Eight(), Suit.HEART),
                Card(Ace(), Suit.SPADE)
            ) to Score(21),
            listOf(
                Card(Two(), Suit.CLUB),
                Card(Three(), Suit.HEART),
                Card(Four(), Suit.SPADE)
            ) to Score(9),
            listOf(
                Card(Seven(), Suit.CLUB),
                Card(Ace(), Suit.SPADE),
                Card(Eight(), Suit.HEART),
                Card(Ace(), Suit.SPADE)
            ) to Score(17),
            listOf(
                Card(Jack(), Suit.CLUB),
                Card(Ace(), Suit.SPADE)
            ) to Score(21),
            listOf(
                Card(Ace(), Suit.CLUB),
                Card(Jack(), Suit.CLUB),
                Card(Queen(), Suit.SPADE)
            ) to Score(21)
        )

        cardSet.forAll {
            val calculateScore = CardsInHand(it.first).calculateScore()
            calculateScore shouldBe it.second
        }
    }
})
