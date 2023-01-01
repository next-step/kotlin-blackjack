package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ParticipantTest : StringSpec({
    "player 가 뽑은 카드의 점수를 계산할 수 있다." {
        val player = Participant("abc")
        player.addCard(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.ACE_10))
        player.score shouldBe 10
    }

    "player 가 가지고 있는 카드 점수의 총합이 21보다 크면 bust 이다." {
        val player = Participant("abc")
        player.addCard(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.ACE_10))
        player.addCard(Card(cardSuit = CardSuit.DIAMOND, denomination = Denomination.ACE_10))
        player.addCard(Card(cardSuit = CardSuit.DIAMOND, denomination = Denomination.FIVE))

        player.isBust() shouldBe true
    }
})
