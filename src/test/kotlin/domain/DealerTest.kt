package domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class DealerTest : FreeSpec({

    "딜러 첫번째 라운드 카드 뽑기" {
        val cardDeck = CardDeck()
        val dealer = Dealer()
        repeat(2) {
            dealer.cards.addCard(cardDeck.drawCard())
        }

        dealer.getPublicCardsOnFirstRound().cards().size shouldBe 1
    }

    "딜러 새로운 카드 뽑을 수 있는지 확인" - {
        "딜러가 가진 카드가 16일 때 가능" {
            val dealer = Dealer()
            dealer.cards.addCard(Card(Suit.HEART, Rank.SIX))
            dealer.cards.addCard(Card(Suit.HEART, Rank.QUEEN))

            dealer.isDrawAvailable() shouldBe true
        }
        "딜러가 가진 카드가 17일 때 불가" {
            val dealer = Dealer()
            dealer.cards.addCard(Card(Suit.HEART, Rank.SEVEN))
            dealer.cards.addCard(Card(Suit.HEART, Rank.QUEEN))

            dealer.isDrawAvailable() shouldBe false
        }
    }

    "딜러 돈 추가" {
        val dealer = Dealer()
        dealer.minusMoney(1000)()

        dealer.money shouldBe 1000
    }

    "딜러 돈 차감" {
        val dealer = Dealer()
        dealer.minusMoney(-1000)()

        dealer.money shouldBe -1000
    }
})
