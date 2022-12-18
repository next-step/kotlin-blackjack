package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 카드 뽑기 조건 만족시 카드를 뽑습니다" {
        val dealer = Dealer()

        dealer.takeCards(Card(CardNumber.TEN, CardShape.CLOVER))

        dealer.canDrawCard() shouldBe true
    }
    "딜러는 카드 뽑기 조건 불만족시 카드를 뽑지않습니다" {
        val dealer = Dealer()

        dealer.takeCards(Card(CardNumber.TEN, CardShape.CLOVER), Card(CardNumber.EIGHT, CardShape.CLOVER))

        dealer.canDrawCard() shouldBe false
    }

    "딜러는 플레이어가 우승한 경우를 판별합니다" {
        val dealer = Dealer()
        val player = Player.withName("우승자")
        dealer.takeCards(Card(CardNumber.TEN, CardShape.CLOVER), Card(CardNumber.EIGHT, CardShape.CLOVER))
        player.takeCards(Card(CardNumber.TEN, CardShape.CLOVER), Card(CardNumber.ACE, CardShape.CLOVER))

        dealer.pickWinner(player) shouldBe player
    }
    "딜러는 자신이 우승한 경우를 판별합니다" {
        val dealer = Dealer()
        val player = Player.withName("우승자")
        dealer.takeCards(Card(CardNumber.TEN, CardShape.CLOVER), Card(CardNumber.ACE, CardShape.CLOVER))
        player.takeCards(Card(CardNumber.TEN, CardShape.CLOVER), Card(CardNumber.TEN, CardShape.CLOVER))

        dealer.pickWinner(player) shouldBe dealer
    }
    "딜러는 무승부인 경우를 판별합니다" {
        val dealer = Dealer()
        val player = Player.withName("우승자")
        dealer.takeCards(Card(CardNumber.TEN, CardShape.CLOVER), Card(CardNumber.TEN, CardShape.CLOVER))
        player.takeCards(Card(CardNumber.TEN, CardShape.CLOVER), Card(CardNumber.TEN, CardShape.CLOVER))

        dealer.pickWinner(player).shouldBeNull()
    }
})
