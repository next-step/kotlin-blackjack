package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ParticipantTest : StringSpec({
    "player 가 가지고 있는 카드 점수의 총합이 21보다 크면 버스트 이다." {
        val player = Participant("abc")
        player.addCard(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.KING))
        player.addCard(Card(cardSuit = CardSuit.DIAMOND, denomination = Denomination.QUEEN))
        player.addCard(Card(cardSuit = CardSuit.DIAMOND, denomination = Denomination.FIVE))

        player.isBust() shouldBe true
    }

    "player 가 초기에 받은 두장의 합이 21점이라면 블랙잭이다." {
        val player = Participant("abc")
        player.addCard(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.KING))
        player.addCard(Card(cardSuit = CardSuit.DIAMOND, denomination = Denomination.ACE))

        player.isBlackJack() shouldBe true
    }

    "player 가 가지고 있는 카드의 합이 21점이라면 최대 점수이다." {
        val player = Participant("abc")
        player.addCard(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.KING))
        player.addCard(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.QUEEN))
        player.addCard(Card(cardSuit = CardSuit.DIAMOND, denomination = Denomination.ACE))

        player.isMaxScore() shouldBe true
    }
})
