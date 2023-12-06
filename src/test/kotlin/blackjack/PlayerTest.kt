package blackjack

import blackjack.domain.BlackjackRule
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Deck
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.State
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveElementAt
import io.kotest.matchers.shouldBe

data class PlayerDrawCardTestData(
    val player: Player,
    val deck: Deck,
)

class PlayerTest : FunSpec({
    context("Player는 이름을 가진다") {
        withData(
            listOf(
                "김영태",
                "기임영태"
            )
        ) { name ->
            val player = Player(name, 0)

            player.name shouldBe name
        }
    }
    context("Player는 Deck에서 카드를 뽑는다.") {
        withData(
            listOf(
                PlayerDrawCardTestData(
                    player = Player("김영태", bet = 0),
                    deck = Deck(
                        listOf(
                            Card(number = CardNumber.ACE, shape = CardShape.SPADE),
                            Card(number = CardNumber.TWO, shape = CardShape.SPADE),
                            Card(number = CardNumber.THREE, shape = CardShape.SPADE),
                        )
                    )
                )
            )
        ) { (player, deck) ->
            val initialCardList = deck.getCardList()

            player.draw(deck)

            player.getCardList().forEachIndexed { index, card ->
                initialCardList.shouldHaveElementAt(initialCardList.count() - 1 - index, card)
            }
        }
    }

    context("Player는 점수가 21점을 초과하면 카드를 뽑을 수 없다.") {
        withData(
            listOf(
                listOf(
                    Card(number = CardNumber.JACK, shape = CardShape.SPADE),
                    Card(number = CardNumber.JACK, shape = CardShape.HEART),
                ),
                listOf(
                    Card(number = CardNumber.JACK, shape = CardShape.SPADE),
                    Card(number = CardNumber.JACK, shape = CardShape.HEART),
                    Card(number = CardNumber.JACK, shape = CardShape.DIAMOND),
                )
            )
        ) { cardList ->
            val player = Player("김영태", bet = 0, hand = Hand(cardList))

            player.canDraw() shouldBe (cardList.sumOf { it.number.score } <= BlackjackRule.TARGET_SCORE)
        }
    }

    context("Player가 처음 뽑은 2장의 점수가 21 미만이면 Hit") {
        val player = Player(
            name = "김영태",
            bet = 0,
            hand = Hand(
                listOf(
                    Card(number = CardNumber.ACE, shape = CardShape.SPADE),
                    Card(number = CardNumber.NINE, shape = CardShape.HEART),
                )
            )
        )

        player.state shouldBe State.HIT
    }

    context("Player의 점수가 21 초과면 Bust") {
        val player = Player(
            name = "김영태",
            bet = 0,
            hand = Hand(
                listOf(
                    Card(number = CardNumber.QUEEN, shape = CardShape.SPADE),
                    Card(number = CardNumber.JACK, shape = CardShape.HEART),
                    Card(number = CardNumber.THREE, shape = CardShape.HEART),
                )
            )
        )

        player.state shouldBe State.BUST
    }

    context("Player가 draw를 멈추면 Stay") {
        val player = Player(
            name = "김영태",
            bet = 0,
            hand = Hand(
                listOf(
                    Card(number = CardNumber.ACE, shape = CardShape.SPADE),
                    Card(number = CardNumber.THREE, shape = CardShape.HEART),
                )
            )
        )
        player.endTurn()

        player.state shouldBe State.STAY
    }

    context("Player가 처음 뽑은 2장의 점수가 21이면 Blackjack") {
        val player = Player(
            name = "김영태",
            bet = 0,
            hand = Hand(
                listOf(
                    Card(number = CardNumber.ACE, shape = CardShape.SPADE),
                    Card(number = CardNumber.JACK, shape = CardShape.HEART),
                )
            )
        )

        player.state shouldBe State.BLACKJACK
    }
})
