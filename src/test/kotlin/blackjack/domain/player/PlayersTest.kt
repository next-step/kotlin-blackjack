package blackjack.domain.player

import blackjack.domain.card.CardFixture.SPADES_ACE
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize

class PlayersTest : FunSpec({
    context("create") {
        test("from raw names") {
            shouldNotThrowAny {
                Players("sun", "justin", "jason")
            }
        }

        test("from players") {
            shouldNotThrowAny {
                Players(
                    listOf(
                        Player("sun"),
                        Player("justin"),
                        Player("jason"),
                    ),
                )
            }
        }
    }

    test("initializeState should give exactly two cards to each player") {
        val playerNames = listOf("sun", "justin", "jason")
        val players = Players(*playerNames.toTypedArray())

        players.initializeState { SPADES_ACE }

        players.values.zip(playerNames).forEach { (player, name) ->
            withClue("Player '$name' should have exactly 2 cards") {
                player.cards shouldHaveSize 2
            }
        }
    }
})
