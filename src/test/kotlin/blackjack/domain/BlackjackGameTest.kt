package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_JACK
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_QUEEN
import blackjack.domain.card.CardTest.Companion.SPADE_THREE
import blackjack.domain.card.CardTest.Companion.SPADE_TWO
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.finished.Stay
import blackjack.domain.gamestate.running.Hit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import java.util.LinkedList

class BlackjackGameTest : FunSpec({

    context("init") {
        test("player가 0명인채로 게임은 생성될 수 없다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { BlackjackGame(players = listOf()) }
            exception.message shouldBe "게임은 최소 1명 이상이 있어야 한다."
        }
    }

    context("firstDraw") {
        test("현재 턴이 firstDraw 턴이 아닌데 요청한 경우 예외가 발생한다.") {
            val blackjackGame = BlackjackGame(turn = TURN_1, players = PLAYERS)
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.firstDraw() }
            exception.message shouldBe "first draw 턴이 아닙니다."
        }

        test("모든 유저에게 2장의 카드를 첫 드로우하고 딜러는 1장의 카드만 보여준다.") {
            val blackjackGame = BlackjackGame(players = PLAYERS)
            val actual = blackjackGame.firstDraw()

            blackjackGame.cardDeck.size() shouldBe 46
            blackjackGame.turn shouldBe TURN_0
            actual shouldHaveSize 3
            actual[0].playerName shouldBe "딜러"
            actual[0].cards shouldHaveSize 1
        }
    }

    context("currentPlayerDraw") {
        test("턴이 유효하지 않은데 드로우하려하면 예외가 발생한다.") {
            val blackjackGame = BlackjackGame(turn = TURN_MINUS_1, players = PLAYERS)
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.currentPlayerDraw() }
            exception.message shouldBe "첫 드로우가 시작되지 않았다."
        }

        test("턴이 종료되었는데 드로우하려하면 예외가 발생한다.") {
            val blackjackGame = BlackjackGame(turn = TURN_2, players = PLAYERS)
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.currentPlayerDraw() }
            exception.message shouldBe "모든 드로우가 종료되었다."
        }

        test("현재 플레이어를 드로우한다.") {
            val cardDeck = LinkedList<Card>()
            cardDeck.add(SPADE_THREE)
            val blackjackGame =
                BlackjackGame(
                    turn = TURN_0,
                    players = listOf(Player.of("a", Hit(Cards.of(SPADE_ACE, SPADE_TWO)))),
                    cardDeck = CardDeck(cardDeck)
                )

            val actual = blackjackGame.currentPlayerDraw()
            actual.playerName shouldBe "a"
            actual.cards shouldContainAll listOf(SPADE_ACE, SPADE_TWO, SPADE_THREE)
        }

        test("드로우 후 플레이어의 상태가 종료되었다면 다음 turn으로 변경한다.") {
            val cardDeck = LinkedList<Card>()
            cardDeck.add(SPADE_QUEEN)
            val blackjackGame =
                BlackjackGame(
                    turn = TURN_0,
                    players = listOf(Player.of("a", Hit(Cards.of(SPADE_KING, SPADE_JACK)))),
                    cardDeck = CardDeck(cardDeck)
                )
            blackjackGame.currentPlayerDraw()

            val actual = blackjackGame.turn
            actual shouldBe TURN_1
        }
    }

    context("isPlayerTurnEnd") {
        forAll(
            row(TURN_MINUS_1, false),
            row(TURN_0, false),
            row(TURN_2, true)
        ) { input, expected ->
            test("현재 턴 ${input}이 종료되었음은 ${expected}이다.") {
                val blackjackGame = BlackjackGame(turn = input, players = PLAYERS)
                val actual = blackjackGame.isPlayerTurnEnd()
                actual shouldBe expected
            }
        }
    }

    context("currentTurnPlayerName") {
        test("턴이 유효하지 않은데 반환하려하면 예외가 발생한다.") {
            val blackjackGame = BlackjackGame(turn = TURN_MINUS_1, players = PLAYERS)
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.currentTurnPlayerName() }
            exception.message shouldBe "첫 드로우가 시작되지 않았다."
        }

        test("턴이 종료되었는데 반환하려하면 예외가 발생한다.") {
            val blackjackGame = BlackjackGame(turn = TURN_2, players = PLAYERS)
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.currentTurnPlayerName() }
            exception.message shouldBe "모든 드로우가 종료되었다."
        }

        test("현재 턴인 유저의 이름이 반환된다.") {
            val blackjackGame = BlackjackGame(turn = TURN_1, players = PLAYERS)
            val actual = blackjackGame.currentTurnPlayerName()
            actual shouldBe "b"
        }
    }

    context("passToNextTurn") {
        test("턴이 유효하지 않은데 전환하려하면 예외가 발생한다.") {
            val blackjackGame = BlackjackGame(turn = TURN_MINUS_1, players = PLAYERS)
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.currentTurnPlayerName() }
            exception.message shouldBe "첫 드로우가 시작되지 않았다."
        }

        test("턴이 종료되었는데 전환하려하면 예외가 발생한다.") {
            val blackjackGame = BlackjackGame(turn = TURN_2, players = PLAYERS)
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.currentTurnPlayerName() }
            exception.message shouldBe "모든 드로우가 종료되었다."
        }

        test("현재 턴 유저를 stay로 변경하고 다음 턴으로 변경한다.") {
            val blackjackGame =
                BlackjackGame(
                    turn = TURN_0,
                    players = listOf(Player.of("a", Hit(Cards.of(SPADE_ACE, SPADE_TWO)))),
                )
            blackjackGame.passToNextTurn()


            blackjackGame.turn shouldBe TURN_1
            (blackjackGame.players[0] as Player).gameState.shouldBeTypeOf<Stay>()
        }
    }

    context("isDealerTurnEnd") {
        test("유저턴이 종료되지 않았는데 호출 시 예외가 발생한다.") {
            val blackjackGame =
                BlackjackGame(
                    turn = TURN_0,
                    players = listOf(Player.of("a", Hit(Cards.of(SPADE_ACE, SPADE_TWO)))),
                )
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.isDealerTurnEnd() }
            exception.message shouldBe "유저턴이 종료되지 않아 확인할 수 없다."
        }

        forAll(
            row(Dealer(Hit(Cards.of(SPADE_TWO, SPADE_THREE))), false),
            row(Dealer(Stay(Cards.of(SPADE_ACE, SPADE_TWO))), true),
        ) { input, expected ->
            test("유저턴이 종료되면 딜러턴을 확인할 수 있다.") {
                val blackjackGame = BlackjackGame(
                    turn = TURN_2,
                    dealer = input,
                    players = PLAYERS
                )
                val actual = blackjackGame.isDealerTurnEnd()
                actual shouldBe expected
            }
        }

    }
}) {
    companion object {
        private val PLAYERS = listOf(Player.from("a"), Player.from("b"))
        private val TURN_MINUS_1 = Turn(-1)
        private val TURN_0 = Turn(0)
        private val TURN_1 = Turn(1)
        private val TURN_2 = Turn(2)
    }
}
