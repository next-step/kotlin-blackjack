package blackjack.model

import blackjack.model.card.CardFixture
import blackjack.model.playable.PlayableResult
import blackjack.model.playable.impl.Dealer
import blackjack.model.playable.impl.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RefereeTest : StringSpec({

    "ACE+ACE+KING 이 들어온 경우 ACE 를 1로 인식해, 블랙잭이 아니어야만 한다" {
        val player = Player(
            "kim",
            CardFixture.makeCards(CardFixture.ace1, CardFixture.ace2, CardFixture.king)
        )
        Referee.isBurst(player) shouldBe false
    }

    "ACE+KING 이 들어온 경우 ACE 를 11로 인식해, 블랙잭으로 계산 되어야 한다" {
        val player = Player(
            "kim",
            CardFixture.makeCards(CardFixture.ace1, CardFixture.king)
        )
        Referee.isBurst(player) shouldBe true
    }

    "딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해야 한다" {
        val player1 = Player(
            "seoul",
            CardFixture.makeCards(CardFixture.ace1, CardFixture.king)

        )
        val player2 = Player(
            "wonju",
            CardFixture.makeCards(CardFixture.queen, CardFixture.king)

        )
        val dealer = Dealer(
            CardFixture.makeCards(CardFixture.five, CardFixture.seven, CardFixture.king)

        )
        val participants = Participants(Players(player1, player2), dealer)
        val actual = Referee.blackJackResult(participants)

        actual.dealerResult().score shouldBe 22
        actual.dealerResult().dealerWinningCount shouldBe 0
        actual.dealerResult().dealerLosingCount shouldBe 2

        actual.playerResult(player1) shouldBe PlayableResult.WIN
        actual.playerResult(player2) shouldBe PlayableResult.WIN
    }
})
