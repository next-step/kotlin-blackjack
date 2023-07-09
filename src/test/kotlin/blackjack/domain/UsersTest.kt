package blackjack.domain

import blackjack.domain.users.Dealer
import blackjack.domain.users.Player
import blackjack.domain.users.Users
import blackjack.enums.Denomination
import blackjack.enums.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UsersTest : StringSpec({
    "플레이어만 블랙잭일 때 플레이어는 배팅 금액의 1.5배를 딜러에게 받는다" {
        val players = listOf(
            Player(
                "Lee",
                Cards(listOf<Card>(Card(Denomination.ACE, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE))),
                10000
            )
        )
        val dealer = Dealer(
            "딜러", Cards(listOf(Card(Denomination.KING, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        )
        val users = Users(players, dealer)

        val gameResult = users.calculateGameResult()
        gameResult.playerResults[0].finalRevenue shouldBe 15000
    }

    "플레이어와 딜러 모두 블랙잭일 때 플레이어는 배팅 금액을 돌려받는다." {
        val players = listOf(
            Player(
                "Lee",
                Cards(listOf<Card>(Card(Denomination.ACE, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE))),
                10000
            )
        )
        val dealer = Dealer(
            "딜러", Cards(listOf(Card(Denomination.ACE, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        )
        val users = Users(players, dealer)

        val gameResult = users.calculateGameResult()
        gameResult.playerResults[0].finalRevenue shouldBe 0
    }
})
