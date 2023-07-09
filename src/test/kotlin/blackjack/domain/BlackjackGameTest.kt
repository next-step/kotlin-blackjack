package blackjack.domain

import blackjack.domain.users.Dealer
import blackjack.domain.users.Player
import blackjack.enums.Denomination
import blackjack.enums.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : StringSpec({
    "카드를 더 받을 수 있는 유저 리스트를 가져온다." {
        val test1 =
            Player(
                "Test1",
                Cards(
                    listOf(
                        Card(Denomination.ACE, Suit.DIAMOND),
                        Card(Denomination.KING, Suit.DIAMOND)
                    )
                ),
                10000
            )
        val test2 =
            Player(
                "Test2",
                Cards(
                    listOf(
                        Card(Denomination.QUEEN, Suit.DIAMOND),
                        Card(Denomination.KING, Suit.DIAMOND)
                    )
                ),
                20000
            )
        val test3 =
            Player(
                "Test3",
                Cards(listOf(Card(Denomination.TWO, Suit.DIAMOND), Card(Denomination.KING, Suit.DIAMOND))),
                10000
            )
        val dealer =
            Dealer(
                "딜러",
                Cards(listOf(Card(Denomination.TWO, Suit.DIAMOND), Card(Denomination.KING, Suit.DIAMOND)))
            )

        val blackjackGame = BlackjackGame(dealer, listOf(test1, test2, test3))

        blackjackGame.cardReceivePossibleUsers().size shouldBe 2
    }
})
