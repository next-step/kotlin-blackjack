package blackjack.domain

import blackjack.domain.result.PlayerResult
import blackjack.domain.users.Dealer
import blackjack.domain.users.Player
import blackjack.enums.Denomination
import blackjack.enums.Suit
import blackjack.model.UserCards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : StringSpec({
    "카드를 더 받을 수 있는 유저 리스트를 가져온다." {
        val test1 =
            Player(
                UserCards(
                    "Test1",
                    Cards(listOf(Card(Denomination.ACE, Suit.DIAMOND), Card(Denomination.KING, Suit.DIAMOND)))
                ),
                PlayerResult(true)
            )
        val test2 =
            Player(
                UserCards(
                    "Test2",
                    Cards(
                        listOf(
                            Card(Denomination.QUEEN, Suit.DIAMOND), Card(Denomination.KING, Suit.DIAMOND)
                        )
                    )
                ),
                PlayerResult(true)
            )
        val test3 =
            Player(
                UserCards(
                    "Test3",
                    Cards(listOf(Card(Denomination.TWO, Suit.DIAMOND), Card(Denomination.KING, Suit.DIAMOND)))
                )
            )
        val dealer =
            Dealer(
                UserCards(
                    "딜러",
                    Cards(listOf(Card(Denomination.TWO, Suit.DIAMOND), Card(Denomination.KING, Suit.DIAMOND)))
                )
            )
        val blackjackGame = BlackjackGame(dealer, listOf(test1, test2, test3))

        blackjackGame.cardReceivePossibleUsers().size shouldBe 1
    }
})
