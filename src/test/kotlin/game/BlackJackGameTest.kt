package game

import card.CardTest.Companion.SPADE_ACE
import card.CardTest.Companion.SPADE_KING
import card.Cards
import card.Deck
import card.Hand
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import player.Name
import player.Player

class BlackJackGameTest : FunSpec({
    context("start.init") {
        test("플레이어는 한명 이상이어야 합니다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { BlackJackGame.Start(emptyList()) }
            exception.message shouldBe "플레이어는 한명이상 존재해야합니다."
        }
    }

    context("start.drawFirstTwoCards") {
        test("모든 유저에게 두개의 카드를 준 후에는 게임이 Play 상태로 넘어가야합니다.") {
            val start = BlackJackGame.Start.startGame(listOf("jeff", "clo"))
            val actual = start.drawFirstTwoCards()
            actual should beInstanceOf<BlackJackGame.Play>()
        }

        forAll(
            row(BlackJackGame.Start(listOf(Player.OnGoing(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_ACE))))))),
            row(BlackJackGame.Start(listOf(Player.Bust(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_ACE))))))),
            row(BlackJackGame.Start(listOf(Player.Stay(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_ACE))))))),
        ) { input ->
            test("플레이어 중에 카드를 몰래 들고 있으면 예외처리를 합니다.") {
                val exception = shouldThrowExactly<IllegalStateException> { input.drawFirstTwoCards() }
                exception.message shouldBe "첫 시작은 빈손이어야합니다."
            }
        }
    }

    context("play.init") {
        forAll(
            row(listOf(Player.Start(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_ACE, SPADE_KING)))))),
            row(listOf(Player.Bust(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_ACE, SPADE_KING)))))),
            row(listOf(Player.Stay(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_ACE, SPADE_KING)))))),
        ) { input ->
            test("onGoing 상태가 아닌 플레이어가 있으면 예외처리 합니다.") {
                val exception = shouldThrowExactly<IllegalArgumentException> { BlackJackGame.Play(input, Deck.of()) }
                exception.message shouldBe "onGoing 만 Play 상태로 넘어갈 수 있습니다."
            }
        }

        forAll(
            row(listOf(Player.OnGoing(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_ACE)))))),
        ) { input ->
            test("onGoing 상태가 아닌 플레이어가 있으면 예외처리 합니다.") {
                val exception = shouldThrowExactly<IllegalArgumentException> { BlackJackGame.Play(input, Deck.of()) }
                exception.message shouldBe "카드는 두장 씩 가지고 있어야 합니다."
            }
        }
    }

    context("play.isEnd") {
        forAll(
            row(listOf(Player.OnGoing(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_ACE, SPADE_KING))))), false),
        ) { input, expected ->
            test("${input}의 isEnd는 $expected 입니다.") {
                val play = BlackJackGame.Play(input, Deck.of())
                val actual = play.isEnd()
                actual shouldBe expected
            }
        }
    }

    context("play.finish") {
        forAll(
            row(listOf(Player.OnGoing(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_ACE, SPADE_KING)))))),
        ) { input ->
            test("게임이 아직 끝나지 않았음에도 끝내려고 하면 예외처리 합니다.") {
                val play = BlackJackGame.Play(input, Deck.of())
                val actual = shouldThrowExactly<IllegalStateException> { play.finish() }
                actual.message shouldBe "게임이 아직 종료되지 않았습니다."
            }
        }
    }
})
