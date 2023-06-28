package blackjack.domain.game

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
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MatchResultTypeTest : StringSpec({

    "플레이어가 버스트 상태면 딜러가 버스트 상태여도 패배한다" {
        val dealer = Dealer()
        dealer.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        dealer.hit(heartKing()) // 12 + k(10) = 22
        val player = player("test")
        player.init(initCard(spadeTen(), spadeFour())) // 10 + 4 = 14
        player.hit(spadeKing()) // 14 + 10 = 24
        MatchResultType.calculatePlayerMatchResult(dealer, player) shouldBe MatchResultType.LOSE
    }

    "딜러가 버스트 상태라면 플레이어의 점수가 낮더라도 승리한다" {
        val dealer = Dealer()
        dealer.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        dealer.hit(heartKing()) // 12 + k(10) = 22
        val player = player("test")
        player.init(initCard(spadeTwo(), spadeFour()))
        MatchResultType.calculatePlayerMatchResult(dealer, player) shouldBe MatchResultType.WIN
    }

    "플레이어의 점수가 딜러의 점수보다 높다면 승리한다" {
        val dealer = Dealer()
        dealer.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        val player = player("test")
        player.init(initCard(spadeKing(), spadeFour())) // k(10) + 4 = 14
        MatchResultType.calculatePlayerMatchResult(dealer, player) shouldBe MatchResultType.WIN
    }

    "플레이어의 점수가 딜러의 점수보다 낮다면 패배한다" {
        val dealer = Dealer()
        dealer.init(initCard(spadeKing(), spadeFour())) // k(10) + 4 = 14
        val player = player("test")
        player.init(initCard(heartQueen(), heartTwo())) // q(10) + 2 = 12
        MatchResultType.calculatePlayerMatchResult(dealer, player) shouldBe MatchResultType.LOSE
    }

    "플레이어의 점수가 딜러의 점수와 같다면 무승부다" {
        val dealer = Dealer()
        dealer.init(initCard(spadeQueen(), spadeFour())) // q(10) + 4 = 14
        val player = player("test")
        player.init(initCard(heartQueen(), heartFour())) // q(10) + 4 = 14
        MatchResultType.calculatePlayerMatchResult(dealer, player) shouldBe MatchResultType.TIE
    }
})
