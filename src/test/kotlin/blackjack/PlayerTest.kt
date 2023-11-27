package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Deck
import blackjack.domain.Player
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
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
            val player = Player(name)

            player.name shouldBe name
        }
    }
    context("Player는 Deck에서 카드를 뽑는다.") {
        withData(
            listOf(
                PlayerDrawCardTestData(
                    player = Player("김영태"),
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
                card shouldBe initialCardList[initialCardList.count() - 1 - index]
            }
        }
    }
})
