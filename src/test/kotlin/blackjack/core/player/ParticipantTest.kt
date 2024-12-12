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
        participant.draw(Card(Denomination.JACK, Suit.SPADES))
        participant.cardNum shouldBe 1
    }

    @Test
    fun `버스트 상태에서 카드 Draw가 되지 않음을 테스트`() {
        val participant = Participant(Name("test"))
        participant.draw(Card(Denomination.JACK, Suit.SPADES))
        participant.draw(Card(Denomination.JACK, Suit.CLUBS))
        participant.draw(Card(Denomination.QUEEN, Suit.SPADES))
        participant.cardNum shouldBe 3

        participant.draw(Card(Denomination.KING, Suit.SPADES))
        participant.cardNum shouldBe 3
    }
}
