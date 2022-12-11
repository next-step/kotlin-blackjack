package model

import helper.PlayerFixture.createDealer
import helper.PlayerFixture.createPlayer
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어 객체를 생성하면, 이름과 카드를 가진다" {
        // when
        val player = Player("나잘함")

        // then
        player.name shouldBe "나잘함"
        player.cards.shouldNotBeNull()
    }

    "플레이어는 Hit 에 카드를 받으면, 가지고 있는 CARDS 에 추가된다" {
        // given
        val player = Player("나잘함")
        // when
        player.hit(Card(PokerNumber.values().first(), PokerShape.values().first()))

        // then
        player.cards.count() shouldBe 1
    }

    "플레이어의 점수가 딜러보다 높으면, 승리를 반환한다" {
        // given
        val player = createPlayer(listOf(PokerNumber.TEN, PokerNumber.ACE))
        val dealer = createDealer()

        // when
        val winOrLose = player.competeWith(dealer)

        // then
        winOrLose shouldBe BlackJackGameResult.WIN
    }

    "플레이어의 점수가 딜러보다 낮으면, 패배를 반환한다" {
        // given
        val player = createPlayer(listOf(PokerNumber.TWO, PokerNumber.THREE))
        val dealer = createDealer()

        // when
        val winOrLose = player.competeWith(dealer)

        // then
        winOrLose shouldBe BlackJackGameResult.LOSE
    }

    "플레이어의 점수가 딜러와 같으면, 무승부를 반환한다" {
        // given
        val player = createPlayer(listOf(PokerNumber.TWO, PokerNumber.TEN))
        val dealer = createDealer()

        // when
        val winOrLose = player.competeWith(dealer)

        // then
        winOrLose shouldBe BlackJackGameResult.DRAW
    }
    "플레이어는 베팅 금액을 추가할 수 있다갖는다" {
        // given
        val player = createPlayer(listOf(PokerNumber.TWO))

        // when
        player.bet = 1000

        // then
        player.bet shouldBe 1000
    }
})
