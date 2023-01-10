package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ParticipantTest : StringSpec({
    "player 가 가지고 있는 카드 점수의 총합이 21보다 크면 버스트여서 플레이어가 카드를 뽑을 수 없다." {
        val cards = Cards(
            mutableListOf(
                Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.KING),
                Card(cardSuit = CardSuit.DIAMOND, denomination = Denomination.QUEEN),
                Card(cardSuit = CardSuit.DIAMOND, denomination = Denomination.FIVE),
            )
        )
        val player = Player("abc", cards)

        player.canHit() shouldBe false
    }

    "player 가 초기에 받은 두장의 합이 21점이라면 블랙잭여서 플레이어가 카드를 뽑을 수 없다." {
        val cards = Cards(
            mutableListOf(
                Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.KING),
                Card(cardSuit = CardSuit.DIAMOND, denomination = Denomination.ACE),
            )
        )
        val player = Player("abc", cards)

        player.canHit() shouldBe false
    }

    "player 가 가지고 있는 카드의 합이 21점이여서 플레이어가 카드를 뽑을 수 없다." {
        val cards = Cards(
            mutableListOf(
                Card(CardSuit.CLOVER, Denomination.KING),
                Card(CardSuit.CLOVER, Denomination.QUEEN),
                Card(CardSuit.DIAMOND, Denomination.ACE)
            )
        )
        val player = Player("abc", cards)

        player.canHit() shouldBe false
    }
})
