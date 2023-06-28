package blackjack.domain.gamer

import blackjack.domain.card.InitCard
import blackjack.domain.card.cards
import blackjack.domain.card.heartFour
import blackjack.domain.card.heartKing
import blackjack.domain.card.heartQueen
import blackjack.domain.card.heartTwo
import blackjack.domain.card.initCard
import blackjack.domain.card.spadeFour
import blackjack.domain.card.spadeKing
import blackjack.domain.card.spadeQueen
import blackjack.domain.card.spadeTen
import blackjack.domain.card.spadeTwo
import blackjack.domain.game.MatchResultType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "초기화를 하지 않았다면 hit가 불가능하다" {
        val player = player("test")
        player.canHit() shouldBe false
    }

    "hit 상태라면 hit가 가능하다" {
        val player = player("test")
        player.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        player.canHit() shouldBe true
    }

    "bust가 되었다면 hit가 불가능하다" {
        val player = player("test")
        player.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        player.hit(heartKing()) // 12 + k(10) = 22
        player.canHit() shouldBe false
    }

    "stay를 했다면 hit가 불가능하다" {
        val player = player("test")
        player.init(initCard(heartQueen(), heartTwo()))
        player.stay()
        player.canHit() shouldBe false
    }

    "플레이어의 카드 목록을 캡쳐할 수 있다" {
        val name = PlayerName("test")
        val player = Player(name)
        val cards = cards(heartQueen(), heartTwo())
        player.init(InitCard(cards))
        player.captureCards() shouldBe PlayerCards(name, cards)
    }

    "플레이어가 버스트 상태면 딜러가 버스트 상태여도 패배한다" {
        val dealer = Dealer()
        dealer.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        dealer.hit(heartKing()) // 12 + k(10) = 22
        val player = player("test")
        player.init(initCard(spadeTen(), spadeFour())) // 10 + 4 = 14
        player.hit(spadeKing()) // 14 + 10 = 24
        player.match(dealer).matchResultType shouldBe MatchResultType.LOSE
    }

    "딜러가 버스트 상태라면 플레이어의 점수가 낮더라도 승리한다" {
        val dealer = Dealer()
        dealer.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        dealer.hit(heartKing()) // 12 + k(10) = 22
        val player = player("test")
        player.init(initCard(spadeTwo(), spadeFour()))
        player.match(dealer).matchResultType shouldBe MatchResultType.WIN
    }

    "플레이어의 점수가 딜러의 점수보다 높다면 승리한다" {
        val dealer = Dealer()
        dealer.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        val player = player("test")
        player.init(initCard(spadeKing(), spadeFour())) // k(10) + 4 = 14
        player.match(dealer).matchResultType shouldBe MatchResultType.WIN
    }

    "플레이어의 점수가 딜러의 점수보다 낮다면 패배한다" {
        val dealer = Dealer()
        dealer.init(initCard(spadeKing(), spadeFour())) // k(10) + 4 = 14
        val player = player("test")
        player.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        player.match(dealer).matchResultType shouldBe MatchResultType.LOSE
    }

    "플레이어의 점수가 딜러의 점수와 같다면 무승부다" {
        val dealer = Dealer()
        dealer.init(initCard(spadeQueen(), spadeFour())) // q(10) + 4 = 14
        val player = player("test")
        player.init(initCard(heartQueen(), heartFour())) // q(10) + 4 = 14
        player.match(dealer).matchResultType shouldBe MatchResultType.TIE
    }
})
