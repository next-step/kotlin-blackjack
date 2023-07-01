package blackjack

import blackjack.controller.Blackjack
import blackjack.dto.PlayerGameResult
import blackjack.utils.arrangeCardsForTest
import blackjack.view.InputView
import blackjack.view.OutputView
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class BlackjackTest : FreeSpec({
    "블랙잭은 특정 순서대로 진행한다." {
        // given
        val inputView = FakeInputView(
            preCommandedNames = listOf("민성", "민수"),
            preConfiguredDrawCommands = listOf(CardDrawCommand.YES, CardDrawCommand.NO, CardDrawCommand.NO)
        )
        val outputView = FakeOutputView()
        val arrangedCards = arrangeCardsForTest(
            Card(Suit.HEART, CardNumber.TWO),
            Card(Suit.SPADE, CardNumber.EIGHT),
            Card(Suit.CLOVER, CardNumber.SEVEN),
            Card(Suit.SPADE, CardNumber.KING),
            Card(Suit.CLOVER, CardNumber.ACE),
        )
        val sut = Blackjack(inputView, outputView)

        // when
        sut.play(GameCardStorage(arrangedCards))

        // then
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

        inputView.fetchCardDrawCommandResultCached shouldContainExactly
                listOf("민성" to CardDrawCommand.YES, "민성" to CardDrawCommand.NO, "민수" to CardDrawCommand.NO)

        outputView.shownCurrentStatuses shouldContainExactly
                listOf(
                    "민성" to listOf(
                        Card(Suit.HEART, CardNumber.TWO),
                        Card(Suit.SPADE, CardNumber.EIGHT),
                        Card(Suit.CLOVER, CardNumber.ACE)
                    ),
                    "민수" to listOf(
                        Card(Suit.CLOVER, CardNumber.SEVEN),
                        Card(Suit.SPADE, CardNumber.KING),
                    ),
                )

        outputView.shownGameResult shouldContainExactly
                listOf(
                    PlayerGameResult(
                        name = "민성",
                        cards = listOf(
                            Card(Suit.HEART, CardNumber.TWO),
                            Card(Suit.SPADE, CardNumber.EIGHT),
                            Card(Suit.CLOVER, CardNumber.ACE),
                        ),
                        score = 21
                    ),
                    PlayerGameResult(
                        name = "민수",
                        cards = listOf(
                            Card(Suit.CLOVER, CardNumber.SEVEN),
                            Card(Suit.SPADE, CardNumber.KING),
                        ),
                        score = 17
                    )
                )
    }
}) {
    private class FakeInputView(
        private val preCommandedNames: List<String>,
        preConfiguredDrawCommands: List<CardDrawCommand>,
    ) : InputView {
        var fetchPlayerNamesCalled = false
        val fetchCardDrawCommandResultCached = mutableListOf<Pair<String, CardDrawCommand>>()
        private val cardDrawCommands = preConfiguredDrawCommands.toMutableList()

        override fun fetchPlayerNames(): List<String> {
            fetchPlayerNamesCalled = true
            return preCommandedNames
        }

        override fun fetchCardDrawCommand(player: Player): CardDrawCommand {
            val preparedCommand = cardDrawCommands.removeFirst()
            fetchCardDrawCommandResultCached.add(player.name to preparedCommand)
            return preparedCommand
        }
    }

    private class FakeOutputView : OutputView {
        lateinit var shownInitialStatus: Map<String, List<Card>>
        lateinit var shownGameResult: List<PlayerGameResult>
        val shownCurrentStatuses = mutableListOf<Pair<String, List<Card>>>()

        override fun showInitialStatus(players: List<Player>) {
            shownInitialStatus = players.associateBy({ it.name }, { it.currentCards.toList() })
        }

        override fun showCurrentStatusOf(player: Player) {
            shownCurrentStatuses.add(player.name to player.currentCards.toList())
        }

        override fun showGameResult(playerGameResults: List<PlayerGameResult>) {
            shownGameResult = playerGameResults
        }
    }
}
