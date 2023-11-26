package blackjack.model.playable.impl

import blackjack.model.Players
import blackjack.model.card.CardFixture
import blackjack.model.pack.impl.ShuffledPack
import blackjack.model.playable.BlackjackScore
import blackjack.model.playblestrategy.impl.DealerStrategy
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 처음에 받은 2장의 카드 점수 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다" {
        val dealer = Dealer(
            CardFixture.makeCards(CardFixture.king, CardFixture.six)
        )
        dealer.playing(DealerStrategy(BlackjackScore(16)), ShuffledPack)

        dealer.cards.cards shouldContain CardFixture.king
        dealer.cards.cards shouldContain CardFixture.six
        dealer.countOfCards() shouldBe 3
    }

    "딜러는 카드의 점수 합계가 17점 이상이면 추가로 받을 수 없다" {
        val dealer = Dealer(
            CardFixture.makeCards(CardFixture.king, CardFixture.seven)
        )
        dealer.playing(DealerStrategy(BlackjackScore(17)), ShuffledPack)

        dealer.cards.cards shouldContain CardFixture.king
        dealer.cards.cards shouldContain CardFixture.seven
        dealer.countOfCards() shouldBe 2
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

        val actualDealerResult = dealer.dealerResult(
            Players(player1, player2)
        )

        actualDealerResult.score shouldBe BlackjackScore(22)
        actualDealerResult.winningCount shouldBe 0
        actualDealerResult.drawingCount shouldBe 0
        actualDealerResult.losingCount shouldBe 2
    }
})
