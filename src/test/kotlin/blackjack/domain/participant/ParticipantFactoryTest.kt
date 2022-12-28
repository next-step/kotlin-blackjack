package blackjack.domain.participant

import blackjack.application.Deck
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.domain.participant.state.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantFactoryTest {
    @Test
    fun `참가자 - 복수 생성 테스트`() {
        // given
        val cards = PlayingCards.shuffle(RandomShuffleStrategy())
        val deck = Deck(cards.toMutableList())
        val names = listOf("pobi", "jason").map { Name(it) }

        // when
        val actual = ParticipantFactory.create(names.toTypedArray(), deck)

        // then
        assertThat(actual.getDealer()).isInstanceOf(Dealer::class.java)
        assertThat(actual.getPlayers().map { it.name }).containsAll(names)
        assertThat(actual.getPlayers().map { it.state }).allMatch { it.cards.size() == NUMBER_OF_STARTING_CARDS }
    }

    companion object {
        private const val NUMBER_OF_STARTING_CARDS = 2
    }
}
