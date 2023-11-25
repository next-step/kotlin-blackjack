package blackjack.game

import blackjack.dealer.DefaultDealerStrategy
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
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
        val newGame = game.dealInitialCards()

        newGame.players.forEach { player ->
            player.cards shouldHaveSize 2
        }
        newGame.dealer.cards shouldHaveSize 2
    }
})
