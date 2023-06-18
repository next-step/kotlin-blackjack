package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Deck
import blackjack.domain.game.BlackjackGame
import blackjack.domain.state.finish.Blackjack
import blackjack.domain.state.running.Hit
import blackjack.event.GameEvent
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
        val rangeLimit = 1..10

        forAll(
            row("안녕하세요이름초과예요"),
            row(""),
        ) { name ->
            val exception = shouldThrow<IllegalArgumentException> {
                mockPlayer(name = name)
            }

            exception shouldHaveMessage "사용 가능한 이름 범위가 아닙니다. 범위 : $rangeLimit, 입력한 이름 : $name"
        }
    }

    "플레이어 이름이 정해진 범위라면 플레이어가 정상적으로 생성된다." {
        forAll(
            row("진원"),
            row("포비"),
        ) { name ->
            val player = mockPlayer(name = name)

            player.getName() shouldBe name
        }
    }

    "플레이어는 게임 플레이를 할 수 있으며, 시작 가능한 상태이고 hit 요청을 하면 드로잉 이벤트가 실행된다." {
        val deck = Deck()

        val player = Player(
            playerName = PlayerName(name = "진원"),
            state = hitState(deck = deck),
        )

        val expect = mutableListOf<String>()

        player.play(
            gameEvent = GameEvent(
                hitOrNot = { true },
                resultEvent = { name, _ -> expect.add(element = name) }
            ),

            drawingEvent = { deck.draw() },
        )

        expect shouldHaveAtLeastSize 1
    }

    "플레이어는 게임 플레이를 할 수 있으며, 시작 가능한 상태이고 hit 요청하지 않으면 드로잉 이벤트가 실행되지 않는다." {
        val deck = Deck()

        val player = Player(
            playerName = PlayerName(name = "진원"),
            state = hitState(deck = deck),
        )

        val expect = mutableListOf<String>()

        player.play(
            gameEvent = GameEvent(
                hitOrNot = { false },
                resultEvent = { name, _ -> expect.add(element = name) }
            ),

            drawingEvent = { deck.draw() },
        )

        expect shouldHaveSize 0
    }

    "플레이어는 게임 플레이를 할 수 있으며, 시작 가능한 상태가 아니라면 아무런 행동을 하지 않는다." {
        val deck = Deck()

        val player = Player(
            playerName = PlayerName(name = "진원"),
            state = Blackjack(playingCards = PlayingCards(cards = mutableSetOf())),
        )

        val expectPlayer = mutableListOf<String>()
        val expectCard = mutableListOf<Card>()

        player.play(
            gameEvent = GameEvent(
                hitOrNot = { false },
                resultEvent = { name, _ -> expectPlayer.add(element = name) }
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

private fun hitState(deck: Deck) = Hit(
    playingCards = PlayingCards(
        cards = deck.multiDraw(
            count = BlackjackGame.INIT_HAND_COUNT,
        ).toMutableSet(),
    ),
)
