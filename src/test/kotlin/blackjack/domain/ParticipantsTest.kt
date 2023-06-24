package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ParticipantsTest : FunSpec({

    test("members") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val dealer = Dealer(cards)
        val player1 = Player("pobi", cards)
        val player2 = Player("jason", cards)
        val players = Players(listOf(player1, player2))

        val participants = Participants(dealer, players)

        participants.members() shouldBe listOf(dealer, player1, player2)
    }

    test("참가자를 생성한다") {
        val participants = Participants.init(
            playerNames = listOf("pobi", "jason"),
            deck = Deck.shuffled(),
        )

        participants.dealer.name shouldBe "딜러"
        participants.players[0].name shouldBe "pobi"
        participants.players[1].name shouldBe "jason"
    }
})
