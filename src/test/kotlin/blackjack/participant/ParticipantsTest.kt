package blackjack.participant

import blackjack.card.Card
import blackjack.card.CardNumber.*
import blackjack.card.CardSuit.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantsTest : StringSpec({
    "딜러의 승률을 계산할 수 있다." {
        // Arrange:
        val dealer = Dealer()
        dealer.take(listOf(Card(TEN, SPADES)))
        val player = Player(PlayerName("player1"))
        player.take(listOf(Card(NINE, SPADES)))
        val players = Players(listOf(player))
        val participants = Participants(dealer, players)

        // Act:
        val dealerRate = participants.getDealerRate()

        // Assert:
        dealerRate.name.value shouldBe "딜러"
        dealerRate.getWinCount() shouldBe 1
        dealerRate.getLoseCount() shouldBe 0
        dealerRate.getDrawCount() shouldBe 0
    }

    "플레이어의 승률을 계산할 수 있다." {
        // Arrange:
        val dealer = Dealer()
        dealer.take(listOf(Card(NINE, SPADES)))
        val player = Player(PlayerName("player1"))
        player.take(listOf(Card(TEN, SPADES)))
        val players = Players(listOf(player))
        val participants = Participants(dealer, players)

        // Act:
        val playerRate = participants.getPlayersRate().first()

        // Assert:
        playerRate.name.value shouldBe "player1"
        playerRate.getWinCount() shouldBe 1
        playerRate.getLoseCount() shouldBe 0
        playerRate.getDrawCount() shouldBe 0
    }

    "버스터되지 않고, 카드 점수가 21에 가까운 참가자가 승리한다. (딜러 = 19, 플레이어 = 22)" {
        // Arrange:
        val dealer = Dealer()
        dealer.take(
            listOf(
                Card(TEN, SPADES),
                Card(NINE, HEARTS),
            )
        )
        val player = Player(PlayerName("player1"))
        player.take(
            listOf(
                Card(TEN, SPADES),
                Card(TEN, HEARTS),
                Card(TWO, HEARTS),
            )
        )
        val players = Players(listOf(player))
        val participants = Participants(dealer, players)

        // Act:
        val dealerRate = participants.getDealerRate()

        // Assert:
        dealerRate.name.value shouldBe "딜러"
        dealerRate.getWinCount() shouldBe 1
        dealerRate.getLoseCount() shouldBe 0
        dealerRate.getDrawCount() shouldBe 0
    }
})