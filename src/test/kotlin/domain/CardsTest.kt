package domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CardsTest : FreeSpec({
    "카드 생성" - {
        "빈 카드로 생성" {
            val cards = Cards()
            cards.cards shouldBe emptyList()
            cards.toString() shouldBe ""
        }

        "카드 추가" {
            val cards = Cards()
            val card = Card(Suit.HEART, Rank.ACE)

            cards.addCard(card)

            cards.cards shouldBe listOf(card)
            cards.toString() shouldBe "A하트"
        }

        "여러 카드 추가" {
            val cards = Cards()
            val card1 = Card(Suit.HEART, Rank.ACE)
            val card2 = Card(Suit.SPADE, Rank.KING)

            cards.addCard(card1)
            cards.addCard(card2)

            cards.cards shouldBe listOf(card1, card2)
            cards.toString() shouldBe "A하트, K스페이드"
        }

        "점수 계산" - {
            "숫자 카드만" {
                val cards = Cards()
                cards.addCard(Card(Suit.HEART, Rank.TWO))
                cards.addCard(Card(Suit.SPADE, Rank.EIGHT))

                cards.calculateScore() shouldBe 10
            }

            "페이스 카드" {
                val cards = Cards()
                cards.addCard(Card(Suit.HEART, Rank.JACK))
                cards.addCard(Card(Suit.SPADE, Rank.QUEEN))
                cards.addCard(Card(Suit.CLUB, Rank.KING))

                cards.calculateScore() shouldBe 30
            }

            "ACE를 11로 계산" {
                val cards = Cards()
                cards.addCard(Card(Suit.HEART, Rank.ACE))
                cards.addCard(Card(Suit.SPADE, Rank.TEN))

                cards.calculateScore() shouldBe 21
            }

            "ACE를 1로 계산 (버스트 방지)" {
                val cards = Cards()
                cards.addCard(Card(Suit.HEART, Rank.ACE))
                cards.addCard(Card(Suit.SPADE, Rank.SIX))
                cards.addCard(Card(Suit.CLUB, Rank.FIVE))

                cards.calculateScore() shouldBe 12
            }

            "여러 ACE 처리" {
                val cards = Cards()
                cards.addCard(Card(Suit.HEART, Rank.ACE))
                cards.addCard(Card(Suit.SPADE, Rank.ACE))
                cards.addCard(Card(Suit.CLUB, Rank.NINE))

                cards.calculateScore() shouldBe 21
            }
        }

        "버스트 판정" - {
            "21점 이하" {
                val cards = Cards()
                cards.addCard(Card(Suit.HEART, Rank.TEN))
                cards.addCard(Card(Suit.SPADE, Rank.ACE))

                cards.calculateScore() shouldBe 21
                cards.isBust() shouldBe false
            }

            "21점 초과" {
                val cards = Cards()
                cards.addCard(Card(Suit.HEART, Rank.KING))
                cards.addCard(Card(Suit.SPADE, Rank.QUEEN))
                cards.addCard(Card(Suit.CLUB, Rank.TWO))

                cards.calculateScore() shouldBe 22
                cards.isBust() shouldBe true
            }
        }
    }
})
