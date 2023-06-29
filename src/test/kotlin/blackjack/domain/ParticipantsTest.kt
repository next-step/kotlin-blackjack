package blackjack.domain

import blackjack.fixture.player
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class ParticipantsTest : FunSpec({

    test("참가자의 모든 인원을 반환한다") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val dealer = Dealer.of(cards)
        val player1 = player(name = "pobi", cards = cards)
        val player2 = player(name = "jason", cards = cards)
        val players = Players(listOf(player1, player2))

        val participants = Participants(dealer, players)

        participants.members() shouldBe listOf(dealer, player1, player2)
    }

    test("참가자를 생성한다") {
        val participants = Participants.init(
            playerInfos = listOf(PlayerInfo("pobi", BigDecimal.ZERO), PlayerInfo("jason", BigDecimal.ZERO)),
            deck = Deck.shuffled(),
        )

        participants.dealer.name shouldBe "딜러"
        participants.players[0].name shouldBe "pobi"
        participants.players[1].name shouldBe "jason"
    }
})
