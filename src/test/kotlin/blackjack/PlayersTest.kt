package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayersTest : FunSpec({
    test("여러 플레이어들을 생성한다.") {
        val cards1 = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val cards2 = Cards(
            Card.of(Denomination.TWO, Suit.HEARTS),
            Card.of(Denomination.THREE, Suit.DIAMONDS),
        )
        val player1 = Player("pobi", cards1)
        val player2 = Player("jason", cards2)

        val players = Players(player1, player2)

        players[0] shouldBe player1
        players[1] shouldBe player2
    }
})
