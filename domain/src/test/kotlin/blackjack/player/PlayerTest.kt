package blackjack.player

import blackjack.card.Card
import blackjack.card.CardRank
import blackjack.card.CardSuit
import blackjack.deck.Deck
import blackjack.deck.FakeCardProvider
import blackjack.hand.Hand
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어는 핸드의 최고 값이 21 이하일 때 카드를 뽑을 수 있어야 한다" {
        val hand = Hand(listOf(Card(CardSuit.SPADES, CardRank.ACE)))
        val player = Player("테스터", hand)
        player.canDrawCard().shouldBeTrue()
    }

    "플레이어가 카드를 뽑을 때 플레이어의 핸드가 업데이트 되어야 한다" {
        val deck = Deck()
        val player = Player("테스터", Hand())

        val newPlayer = player.drawCard(deck)
        newPlayer.cards.size shouldBe 1
    }

    "덱이 비어있을 때 카드를 뽑으려고 하면 예외가 발생해야 한다" {
        val emptyDeck = Deck(cardProvider = FakeCardProvider())
        val player = Player("테스터", Hand())

        val exception = shouldThrow<IllegalStateException> {
            player.drawCard(emptyDeck)
        }
        exception.message shouldBe "덱에 카드가 없으면 카드를 뽑을 수 없습니다."
    }

    "플레이어의 최고 값 계산이 올바르게 수행되어야 한다: A스페이드 + 10다이아는 21이다." {
        val hand = Hand(listOf(Card(CardSuit.SPADES, CardRank.ACE), Card(CardSuit.DIAMONDS, CardRank.TEN)))
        val player = Player("테스터", hand)
        player.calculateBestValue() shouldBe 21
    }
})
