package blackjack

import blackjack.controller.Blackjack
import blackjack.utils.arrangeCardsForTest
import blackjack.view.InputView
import blackjack.view.OutputView
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BlackjackTest : FreeSpec({
    "블랙잭은 특정 순서대로 진행한다." {
        val inputView = FakeInputView(listOf("민성", "민수"), listOf("y", "n", "n"))
        val outputView = FakeOutputView()
        val arrangedCards = arrangeCardsForTest(
            Card(Suit.HEART, CardNumber.TWO),
            Card(Suit.SPADE, CardNumber.EIGHT),
            Card(Suit.CLOVER, CardNumber.SEVEN),
            Card(Suit.SPADE, CardNumber.KING),
            Card(Suit.CLOVER, CardNumber.ACE),
        )
        val sut = Blackjack(inputView, outputView)

        sut.play(GameCardStorage(arrangedCards))

        inputView.fetchPlayerNamesCalled shouldBe true
        outputView.shownInitialStatus shouldBe mapOf(
            "민성" to listOf(
                Card(Suit.HEART, CardNumber.TWO),
                Card(Suit.SPADE, CardNumber.EIGHT)
            ),
            "민수" to listOf(
                Card(Suit.CLOVER, CardNumber.SEVEN),
                Card(Suit.SPADE, CardNumber.KING),
            ),
        )

    }

}) {
    private class FakeInputView(
        private val preCommandedNames: List<String>,
        private val preCommandedCardDrawChoice: List<String>,
    ) : InputView {
        var fetchPlayerNamesCalled = false

        override fun fetchPlayerNames(): List<String> {
            fetchPlayerNamesCalled = true
            return preCommandedNames
        }
    }

    private class FakeOutputView : OutputView {
        lateinit var shownInitialStatus: Map<String, List<Card>>

        override fun showInitialStatus(players: List<Player>) {
            shownInitialStatus = players.associateBy({ it.name }, { it.currentCards.toList() })
        }
    }
}
