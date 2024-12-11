package blackjack.core.player

import blackjack.core.card.Card
import blackjack.core.card.Denomination
import blackjack.core.card.Suit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `카드 Draw를 테스트한다`() {
        val participant = Participant(Name("test"))

        participant.point shouldBe 0

        participant.draw(Card(Denomination.JACK, Suit.SPADES))
        participant.point shouldBe 10

        participant.draw(Card(Denomination.JACK, Suit.SPADES))
        participant.point shouldBe 10

        participant.draw(Card(Denomination.QUEEN, Suit.SPADES))
        participant.point shouldBe 20

        participant.draw(Card(Denomination.KING, Suit.SPADES))
        participant.point shouldBe 30
        participant.cards.checkBust() shouldBe true
    }
}
