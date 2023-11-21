package blackjack.player

import blackjack.card.Card
import blackjack.card.CardRank
import blackjack.card.CardSuit
import blackjack.hand.Hand
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어는 핸드의 최고 값이 21 이하일 때 카드를 뽑을 수 있어야 한다" {
        val hand = Hand(listOf(Card(CardSuit.SPADES, CardRank.ACE)))
        val player = Player("테스터", hand)
        player.canReceiveCard().shouldBeTrue()
    }

    "플레이어가 카드를 뽑을 때 플레이어의 핸드가 업데이트 되어야 한다" {
        val player = Player("테스터", Hand())
        player.cards.size shouldBe 0
        val newPlayer = player.receiveCard(card = Card(CardSuit.SPADES, CardRank.ACE))
        newPlayer.cards.size shouldBe 1
    }

    "플레이어의 최고 값 계산이 올바르게 수행되어야 한다: A스페이드 + 10다이아는 21이다." {
        val hand = Hand(listOf(Card(CardSuit.SPADES, CardRank.ACE), Card(CardSuit.DIAMONDS, CardRank.TEN)))
        val player = Player("테스터", hand)
        player.calculateBestValue() shouldBe 21
    }
})
