package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class GameTest : StringSpec({
    "Game should distribute initial cards to dealer and players" {
        val dealer = Dealer()
        val players = Players(listOf(Player("pobi"), Player("jason")))
        val participants = GameParticipants(dealer, players)
        val deck = Deck()
        val game = Game(deck, participants)

        game.start()

        dealer.getCards().size shouldBe 2
        players.getPlayers().forEach {
            it.getCards().size shouldBe 2
        }
    }

    "Game should handle dealer bust scenario" {
        val dealer =
            Dealer().apply {
                addCard(Card(Rank.TEN, Suit.HEART))
                addCard(Card(Rank.TEN, Suit.DIAMOND))
                addCard(Card(Rank.TWO, Suit.CLUB))
            }
        val players =
            Players(
                listOf(
                    Player("pobi").apply {
                        addCard(Card(Rank.FIVE, Suit.SPADE))
                        addCard(Card(Rank.THREE, Suit.HEART))
                    },
                ),
            )
        val participants = GameParticipants(dealer, players)
        val deck = Deck()
        val game = Game(deck, participants)

        val result = game.calculateResults()

        result.dealerScore shouldBe dealer.getTotalValue()
        result.playerResults.shouldContainExactly(
            GameResult.PlayerGameResult("pobi", GameResult.Result.WIN),
        )
    }

    "Game should handle player wins and losses" {
        val dealer =
            Dealer().apply {
                addCard(Card(Rank.TEN, Suit.HEART))
                addCard(Card(Rank.SIX, Suit.CLUB))
            }
        val players =
            Players(
                listOf(
                    Player("pobi").apply {
                        addCard(Card(Rank.TEN, Suit.SPADE))
                        addCard(Card(Rank.SEVEN, Suit.HEART))
                    },
                    Player("jason").apply {
                        addCard(Card(Rank.EIGHT, Suit.DIAMOND))
                        addCard(Card(Rank.FOUR, Suit.HEART))
                    },
                ),
            )
        val participants = GameParticipants(dealer, players)
        val deck = Deck()
        val game = Game(deck, participants)

        val result = game.calculateResults()

        result.dealerScore shouldBe dealer.getTotalValue()
        result.playerResults.shouldContainExactly(
            GameResult.PlayerGameResult("pobi", GameResult.Result.WIN),
            GameResult.PlayerGameResult("jason", GameResult.Result.LOSE),
        )
    }
})
