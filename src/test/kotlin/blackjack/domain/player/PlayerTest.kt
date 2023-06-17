package blackjack.domain.player

import blackjack.domain.behavior.BustState
import blackjack.domain.behavior.FinishState
import blackjack.domain.behavior.StartState
import blackjack.domain.behavior.mockState
import blackjack.domain.card.Card
import blackjack.domain.card.InitPlayingCards
import blackjack.domain.card.MockPlayingCards
import blackjack.domain.deck.Deck
import blackjack.domain.game.GameEvent
import blackjack.domain.model.BlackJackErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayerTest : StringSpec({

    "플레이어 이름이 정해진 범위가 아니라면 정해진 범위가 아니라는 에러 메시지를 반환한다." {
        forAll(
            row("안녕하세요이름초과예요"),
            row(""),
        ) { name ->
            val exception = shouldThrow<IllegalArgumentException> {
                Player(name = name, state = mockState)
            }

            exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_USED_RANGE_OF_NAME_LENGTH.message(
                arrayOf(1..10, name.trim())
            )
        }
    }

    "플레이어 이름이 정해진 범위라면 플레이어가 정상적으로 생성된다." {
        forAll(
            row("진원"),
            row("포비"),
        ) { name ->
            val player = Player(name = name, state = mockState)

            player.name shouldBe name
        }
    }

    "플레이어는 게임 플레이를 할 수 있으며, 시작 가능한 상태이고 hit 요청을 하면 드로잉 이벤트가 실행된다." {
        val deck = Deck()

        val player = Player(
            name = "진원",
            state = startState(deck = deck),
        )

        val expect = mutableListOf<Player>()

        player.play(
            gameEvent = GameEvent(
                hitOrNot = { true },
                resultEvent = { expect.add(element = it) }
            ),

            drawingEvent = { deck.draw() },
        )

        player.state::class shouldBe BustState::class
        expect shouldHaveAtLeastSize 1
    }

    "플레이어는 게임 플레이를 할 수 있으며, 시작 가능한 상태이고 hit 요청하지 않으면 드로잉 이벤트가 실행되지 않는다." {
        val deck = Deck()

        val player = Player(
            name = "진원",
            state = startState(deck = deck),
        )

        val expect = mutableListOf<Player>()

        player.play(
            gameEvent = GameEvent(
                hitOrNot = { false },
                resultEvent = { expect.add(element = it) }
            ),

            drawingEvent = { deck.draw() },
        )

        player.state::class shouldBe FinishState::class
        expect shouldHaveSize 1
    }

    "플레이어는 게임 플레이를 할 수 있으며, 시작 가능한 상태가 아니라면 아무런 행동을 하지 않는다." {
        val deck = Deck()

        val player = Player(
            name = "진원",
            state = FinishState(playingCards = MockPlayingCards(cards = mutableSetOf())),
        )

        val expectPlayer = mutableListOf<Player>()
        val expectCard = mutableListOf<Card>()

        player.play(
            gameEvent = GameEvent(
                hitOrNot = { false },
                resultEvent = { expectPlayer.add(element = it) }
            ),

            drawingEvent = {
                val element = deck.draw()
                expectCard.add(element = element)
                element
            },
        )

        expectPlayer shouldHaveSize 0
        expectCard shouldHaveSize 0
    }
})

private fun startState(deck: Deck) = StartState(
    playingCards = InitPlayingCards(cards = deck.multiDraw(InitPlayingCards.INIT_CARD_COUNT)),
)
