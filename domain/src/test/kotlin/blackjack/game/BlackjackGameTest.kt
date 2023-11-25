package blackjack.game

import action.BlackJackAction
import blackjack.dealer.DefaultDealerStrategy
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.kotest.matchers.types.shouldBeInstanceOf

class BlackjackGameTest : StringSpec({

    "플레이어 추가 확인" {
        val game = blackjackOpen {
            join("Alice")
            join("Bob")
        }
        game.players.map { it.name } shouldContainExactly listOf("Alice", "Bob")
    }

    "딜러 전략 설정 확인" {
        val game = blackjackOpen {
            join("Alice")
            join("Bob")
            dealerStrategy(DealerStrategyType.DEFAULT_DEALER_STRATEGY)
        }
        game.dealer.dealerStrategy.shouldBeInstanceOf<DefaultDealerStrategy>()
    }

    "게임 인스턴스 생성 확인" {
        val game = blackjackOpen {
            join("Charlie")
        }
        game shouldNotBe null
    }

    "플레이어 없이 게임 생성 시 예외 발생 확인" {
        val exception = shouldThrow<IllegalArgumentException> {
            blackjackOpen { }
        }
        exception shouldHaveMessage "플레이어가 최소 한 명은 존재해야 합니다."
    }

    "초기 카드 배분 확인" {
        val game = blackjackOpen {
            join("Alice")
            join("Bob")
        }
        game.dealInitialCards()

        game.players.forEach { player ->
            player.cards shouldHaveSize 2
        }
        game.dealer.cards shouldHaveSize 2
    }

    "플레이어 턴 처리 확인" {
        val game = blackjackOpen {
            join("Alice")
            join("Bob")
        }
        game.dealInitialCards()
        val alice = game.players.first { it.name == "Alice" }

        // Alice가 턴을 끝냄
        game.dealPlayerTurn(alice, false)
        alice.cards shouldHaveSize 2

        // Bob의 턴으로 넘어감
        val bob = game.players.first { it.name == "Bob" }
        (game.state as GameState.PlayerTurn).currentPlayer shouldBe bob
    }

    "딜러 턴 처리 확인" {
        val game = blackjackOpen {
            join("Alice")
            join("Bob")
        }
        game.dealInitialCards()
        // 모든 플레이어의 턴을 종료
        game.players.forEach { game.dealPlayerTurn(it, false) }

        // 딜러 턴 시작
        game.state.shouldBeInstanceOf<GameState.DealerTurn>()
    }

    "게임 결과 계산 확인" {
        val game = blackjackOpen {
            join("Alice")
            join("Bob")
        }
        game.dealInitialCards()
        // 모든 턴 종료
        game.players.forEach { game.dealPlayerTurn(it, false) }
        game.dealDealerTurn()

        val results = game.calculateResult()
        results.keys shouldContainExactlyInAnyOrder game.players + game.dealer
    }
})
