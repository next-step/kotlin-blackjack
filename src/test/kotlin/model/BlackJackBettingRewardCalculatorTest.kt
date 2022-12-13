package model

import helper.PlayerFixture.createPlayer
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackBettingRewardCalculatorTest : StringSpec({
    "배팅을 1000원 하고, 처음 두장의 카드합이 21인경우,  배팅금액의 1.5배를 딜러에게 받는다" {
        // given
        val player = createPlayer(listOf(PokerNumber.ACE, PokerNumber.TEN))
        player.bet = 1000
        val dealer = createPlayer(listOf(PokerNumber.TWO, PokerNumber.TEN))

        // when
        val calculator = BlackJackBettingRewardCalculator(player, dealer)

        // then
        calculator.reward() shouldBe 1500
    }

    "배팅을 1000원 하고, 딜려와 플레이어가 모두 블랙잭인경우 플레이어는 베팅한 금액을 보상을 받는다" {
        // given
        val player = createPlayer(listOf(PokerNumber.ACE, PokerNumber.FOUR, PokerNumber.SIX))
        player.bet = 1000
        val dealer = createPlayer(listOf(PokerNumber.ACE, PokerNumber.TEN))

        // when
        val calculator = BlackJackBettingRewardCalculator(player, dealer)

        // then
        calculator.reward() shouldBe 1000
    }

    "배팅을 1000원 하고, 딜려보다 플레이어가 스코어가 높은경우  플레이어는 베팅한 금액을 보상을 받는다" {
        // given
        val player = createPlayer(listOf(PokerNumber.ACE, PokerNumber.FOUR, PokerNumber.SIX))
        player.bet = 1000
        val dealer = createPlayer(listOf(PokerNumber.ACE, PokerNumber.FOUR))

        // when
        val calculator = BlackJackBettingRewardCalculator(player, dealer)

        // then
        calculator.reward() shouldBe 1000
    }

    "배팅을 1000원 하고, 딜려와 플레이어가 스코어가 같은 경우 플레이어는 돌려받을 금액이 없다" {
        // given
        val player = createPlayer(listOf(PokerNumber.ACE, PokerNumber.FOUR))
        player.bet = 1000
        val dealer = createPlayer(listOf(PokerNumber.ACE, PokerNumber.FOUR))

        // when
        val calculator = BlackJackBettingRewardCalculator(player, dealer)

        // then
        calculator.reward() shouldBe 0
    }

    "배팅을 1000원 하고, 딜려가 플레이어보다 스코어가 높은 경우 플레이어는 배팅액을 손해본다" {
        // given
        val player = createPlayer(listOf(PokerNumber.ACE, PokerNumber.TWO))
        player.bet = 1000
        val dealer = createPlayer(listOf(PokerNumber.ACE, PokerNumber.FOUR))

        // when
        val calculator = BlackJackBettingRewardCalculator(player, dealer)

        // then
        calculator.reward() shouldBe -1000
    }
})
