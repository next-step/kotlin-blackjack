package blackjack.domain

import blackjack.enums.Denomination
import blackjack.enums.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : StringSpec({
    "카드를 더 받을 수 있는 유저 리스트를 가져온다." {
        val test1 =
            User(
                "Test1",
                Cards(listOf(Card(Denomination.ACE, Suit.DIAMOND), Card(Denomination.KING, Suit.DIAMOND))),
                true
            )
        val test2 =
            User(
                "Test2",
                Cards(listOf(Card(Denomination.QUEEN, Suit.DIAMOND), Card(Denomination.KING, Suit.DIAMOND))),
                true
            )
        val test3 =
            User("Test3", Cards(listOf(Card(Denomination.TWO, Suit.DIAMOND), Card(Denomination.KING, Suit.DIAMOND))))

        val blackjackGame = BlackjackGame(listOf(test1, test2, test3))

        blackjackGame.cardReceivePossibleUsers().size shouldBe 1
    }
})
