package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Denomination
import blackjack.domain.Hand
import blackjack.domain.Suite
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class PlayerNameInputViewTest : FreeSpec({
    val deck = Deck(
        listOf(
            Card(Suite.SPADES, Denomination.ACE),
            Card(Suite.HEARTS, Denomination.TWO),
            Card(Suite.CLUBS, Denomination.THREE),
            Card(Suite.DIAMONDS, Denomination.FOUR),
        )
    )
    val dealer = Dealer(deck)

    "참가자 이름입력 안내문구를 출력한다" {
        val io = StubIO()
        val view = PlayerNameInputView(io, dealer)
        io.addInput("user")

        view.run()

        io.printed shouldBe listOf("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)", "")
    }

    "잘못 입력하는 경우 다시 입력받게 한다" - {
        listOf(
            "공백 입력" to "",
            "쉼표만 입력" to ",,,",
        ).forEach { (message, input) ->
            message {
                val io = StubIO()
                val view = PlayerNameInputView(io, dealer)
                io.addInput(input)
                io.addInput("user")

                view.run()

                io.printed shouldHaveSize 2
                io.printed.last() shouldBe "잘못 입력하셨습니다. 다시 입력하세요."
            }
        }
    }

    "카드 2장을 가진 참가자를 생성한다" {
        val io = StubIO()
        val view = PlayerNameInputView(io, dealer)
        io.addInput("user1,user2")

        val players = view.run()

        players.map { it.name } shouldBe listOf("user1", "user2")
        players.map { it.hand } shouldBe listOf(
            Hand(
                listOf(
                    Card(Suite.SPADES, Denomination.ACE),
                    Card(Suite.HEARTS, Denomination.TWO),
                ),
            ),
            Hand(
                listOf(
                    Card(Suite.CLUBS, Denomination.THREE),
                    Card(Suite.DIAMONDS, Denomination.FOUR),
                )
            ),
        )
    }
})
