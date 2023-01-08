package blackjack.domain

import domain.algorithm.DefaultScoreOptimizationAlgorithm
import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Cards
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldNotBeTrue
import io.kotest.matchers.shouldBe

class CardsTest : FreeSpec({
    "Cards 는 " - {
        val cardList = listOf(
            Card(CardShape.HEART, CardNumber.JACK),
            Card(CardShape.DIAMOND, CardNumber.QUEEN),
            Card(CardShape.CLOVER, CardNumber.TWO)
        )
        val cards = Cards(DefaultScoreOptimizationAlgorithm, cardList, 23)

        "개수를 확인할 수 있다" {
            cards.count shouldBe 3
        }

        "카드 스코어를 확인할 수 있다" {
            val score = cards.score()
            score shouldBe 22
        }

        "카드 개수를 추가할 수 있다." {
            val beforeCard = cards.count
            val card = Card(CardShape.CLOVER, CardNumber.ACE)
            cards.add(card)
            cards.count shouldBe beforeCard + 1
        }

        "카드 스코어 인자에 따라 받을 수 있는지가 달라진다" - {
            "제한 카드 스코어에 따라 이용 가능한지가 알 수 있다" {
                val targetCardList = listOf(
                    Card(CardShape.HEART, CardNumber.JACK),
                    Card(CardShape.DIAMOND, CardNumber.QUEEN),
                    Card(CardShape.CLOVER, CardNumber.TWO)
                )
                val targetCards = Cards(DefaultScoreOptimizationAlgorithm, targetCardList, 23)
                targetCards.isAvailableReceiveNumber().shouldBeTrue()
            }

            "제한 카드 스코어에 따라 이용 가능한지가 저장된다" {
                val targetCardList = listOf(
                    Card(CardShape.HEART, CardNumber.JACK),
                    Card(CardShape.DIAMOND, CardNumber.QUEEN),
                    Card(CardShape.CLOVER, CardNumber.TWO)
                )
                val targetCards = Cards(DefaultScoreOptimizationAlgorithm, targetCardList, 5)
                targetCards.isAvailableReceiveNumber().shouldNotBeTrue()
            }
        }
    }
})