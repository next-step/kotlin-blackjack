package blackjack.domain.participant

import blackjack.ClubJack
import blackjack.ClubTwo
import blackjack.SpadeAce
import blackjack.SpadeJack
import blackjack.application.Deck
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.rule.Blackjack
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.domain.participant.Participants.Companion.createPlayers
import blackjack.domain.participant.state.Name
import blackjack.domain.participant.state.role.Dealer
import blackjack.domain.participant.state.role.Player
import blackjack.domain.participant.state.role.Role.Companion.NUMBER_OF_STARTING_CARDS
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `참가자 - 생성 테스트`() {
        // given
        val cards = PlayingCards(SpadeAce, SpadeJack)
        val player1 = Player("pobi", cards)
        val player2 = Player("jason", cards)

        // when
        val actual = Participants(player1, player2)

        // then
        assertThat(actual.getPlayers().map { it.name.toString() }).containsExactly("pobi", "jason")
        assertThat(actual.getPlayers().map { it.state is Blackjack }).containsExactly(true, true)
    }

    @Test
    fun `참가자 - 생성 실패 예외처리 테스트, 참차자가 2명 미만인 경우`() {
        // given
        val cards = PlayingCards(SpadeAce, SpadeJack)
        val player = Player("pobi", cards)

        // when, then
        assertThatThrownBy { Participants(player) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("참가자는 2명 이상이어야 합니다.")
    }

    @Test
    fun `참가자 - 생성 실패 예외처리 테스트, 참가자가 중복되는 경우`() {
        // given
        val cards = PlayingCards(SpadeAce, SpadeJack)
        val players = listOf(Player("jason", cards), Player("jason", cards))

        // when, then
        assertThatThrownBy { Participants(players) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("참가자의 이름은 중복될 수 없습니다.")
    }

    @Test
    fun `참가자 - 딜러, 플레이어 가져오기 테스트`() {
        // given
        val cards = PlayingCards(SpadeAce, SpadeJack)
        val dealer = Dealer(cards)
        val player1 = Player("pobi", cards)
        val player2 = Player("jason", cards)
        val participants = Participants(dealer, player1, player2)

        // when, then
        assertThat(participants.getDealer().isDealer()).isTrue
        assertThat(participants.getPlayers().map { it.name.toString() }).containsExactly("pobi", "jason")
    }

    @Test
    fun `참가자 - 복수 참가자 생성 테스트`() {
        // given
        val cards = PlayingCards.shuffle(RandomShuffleStrategy())
        val deck = Deck(cards.toMutableList())
        val names = listOf("pobi", "jason").map { Name(it) }

        // when
        val actual = createPlayers(names.toTypedArray(), deck)

        // then
        assertThat(actual.getPlayers().map { it.name }).containsAll(names)
        assertThat(actual.getPlayers().map { it.state }).allMatch { it.cards.size() == NUMBER_OF_STARTING_CARDS }
    }

    @Test
    fun `참가자 - 참가자 합치기 테스트`() {
        // given
        val cards = PlayingCards(SpadeAce, SpadeJack)
        val dealer = Dealer(cards)
        val players = Participants(Player("pobi", cards), Player("jason", cards))

        // when
        val actual = players + dealer

        // then
        assertThat(actual.getDealer().isDealer()).isTrue
        assertThat(actual.getPlayers().map { it.name.toString() }).containsExactly("pobi", "jason")
        assertThat(actual.getAll()).size().isEqualTo(3)
    }

    @Test
    fun `참가자 - 블랙잭 확인 테스트`() {
        // given
        val players = Participants(
            Player("pobi", PlayingCards(SpadeAce, SpadeJack)),
            Player("jason", PlayingCards(ClubTwo, ClubJack))
        )

        // when
        val actual = players.isBlackjack()

        // then
        assertThat(actual).isTrue
    }
}
