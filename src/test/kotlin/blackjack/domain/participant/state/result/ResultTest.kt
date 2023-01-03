package blackjack.domain.participant.state.result

import blackjack.Player
import blackjack.SpadeAce
import blackjack.SpadeEight
import blackjack.SpadeFive
import blackjack.SpadeFour
import blackjack.SpadeJack
import blackjack.SpadeKing
import blackjack.SpadeNine
import blackjack.SpadeQueen
import blackjack.SpadeSeven
import blackjack.SpadeSix
import blackjack.SpadeTen
import blackjack.SpadeThree
import blackjack.SpadeTwo
import blackjack.domain.card.PlayingCards
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.result.Result.Companion.calculateProfit
import blackjack.domain.participant.state.result.Result.Companion.calculateResult
import blackjack.domain.participant.state.role.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun `딜러 2승 - 딜러(블랙잭), 참가자1(18), 참가자2(19)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeAce, SpadeTen))
        val player1 = Player("pobi", PlayingCards(SpadeEight, SpadeJack))
        val player2 = Player("jason", PlayingCards(SpadeNine, SpadeQueen))
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateResult(participants)

        // then
        assertThat(actual).isEqualTo(
            mapOf(
                player1 to Result.Lose,
                player2 to Result.Lose,
            )
        )
    }

    @Test
    fun `딜러 2무 - 딜러(17), 참가자1(17), 참가자2(17)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeSeven, SpadeJack))
        val player1 = Player("pobi", PlayingCards(SpadeEight, SpadeNine))
        val player2 = Player("jason", PlayingCards(SpadeTwo, SpadeFive, SpadeTen))
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateResult(participants)

        // then
        assertThat(actual).isEqualTo(
            mapOf(
                player1 to Result.Draw,
                player2 to Result.Draw,
            )
        )
    }

    @Test
    fun `딜러 2패 - 딜러(14), 참가자1(20), 참가자2(21)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeFour, SpadeJack))
        val player1 = Player("pobi", PlayingCards(SpadeQueen, SpadeKing))
        val player2 = Player("jason", PlayingCards(SpadeSix, SpadeFive, SpadeTen))
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateResult(participants)

        // then
        assertThat(actual).isEqualTo(
            mapOf(
                player1 to Result.Win,
                player2 to Result.Win,
            )
        )
    }

    @Test
    fun `딜러 1승 1패 - 딜러(20), 참가자1(21), 참가자2(19)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTen, SpadeJack))
        val player1 = Player("pobi", PlayingCards(SpadeSix, SpadeFive, SpadeTen))
        val player2 = Player("jason", PlayingCards(SpadeSix, SpadeFive, SpadeEight))
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateResult(participants)

        // then
        assertThat(actual).isEqualTo(
            mapOf(
                player1 to Result.Win,
                player2 to Result.Lose,
            )
        )
    }

    @Test
    fun `딜러 1승 1무 - 딜러(20), 참가자1(20), 참가자2(19)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTen, SpadeJack))
        val player1 = Player("pobi", PlayingCards(SpadeQueen, SpadeKing))
        val player2 = Player("jason", PlayingCards(SpadeSix, SpadeFive, SpadeEight))
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateResult(participants)

        // then
        assertThat(actual).isEqualTo(
            mapOf(
                player1 to Result.Draw,
                player2 to Result.Lose,
            )
        )
    }

    @Test
    fun `딜러 1무 1패 - 딜러(20), 참가자1(20), 참가자2(21)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTen, SpadeJack))
        val player1 = Player("pobi", PlayingCards(SpadeQueen, SpadeKing))
        val player2 = Player("jason", PlayingCards(SpadeSix, SpadeSeven, SpadeEight))
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateResult(participants)

        // then
        assertThat(actual).isEqualTo(
            mapOf(
                player1 to Result.Draw,
                player2 to Result.Win,
            )
        )
    }

    @Test
    fun `배당금 계산 - 딜러(블랙잭), 참가자1(블랙잭), 참가자2(20)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeAce, SpadeJack))
        val player1 = Player("pobi", PlayingCards(SpadeAce, SpadeQueen), 1000)
        val player2 = Player("jason", PlayingCards(SpadeTen, SpadeKing), 1000).stay()
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(1000.0)
        assertThat(actual[player1]).isEqualTo(0.0)
        assertThat(actual[player2]).isEqualTo(-1000.0)
    }

    @Test
    fun `배당금 계산 - 딜러(블랙잭), 참가자1(블랙잭), 참가자2(블랙잭)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeAce, SpadeJack))
        val player1 = Player("pobi", PlayingCards(SpadeAce, SpadeQueen), 1000)
        val player2 = Player("jason", PlayingCards(SpadeAce, SpadeKing), 1000)
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(0.0)
        assertThat(actual[player1]).isEqualTo(0.0)
        assertThat(actual[player2]).isEqualTo(0.0)
    }

    @Test
    fun `배당금 계산 - 딜러(버스트), 참가자1(블랙잭), 참가자2(20)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTen, SpadeJack, SpadeTwo))
        val player1 = Player("pobi", PlayingCards(SpadeAce, SpadeQueen), 1000)
        val player2 = Player("jason", PlayingCards(SpadeTen, SpadeKing), 1000).stay()
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(-2500.0)
        assertThat(actual[player1]).isEqualTo(1500.0)
        assertThat(actual[player2]).isEqualTo(1000.0)
    }

    @Test
    fun `배당금 계산 - 딜러(버스트), 참가자1(버스트), 참가자2(20)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTen, SpadeJack, SpadeTwo))
        val player1 = Player("pobi", PlayingCards(SpadeSeven, SpadeEight, SpadeNine), 1000)
        val player2 = Player("jason", PlayingCards(SpadeTen, SpadeKing), 1000).stay()
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(0.0)
        assertThat(actual[player1]).isEqualTo(-1000.0)
        assertThat(actual[player2]).isEqualTo(1000.0)
    }

    @Test
    fun `배당금 계산 - 딜러(19), 참가자1(20), 참가자2(18)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTen, SpadeNine))
        val player1 = Player("pobi", PlayingCards(SpadeJack, SpadeQueen), 1000).stay()
        val player2 = Player("jason", PlayingCards(SpadeKing, SpadeEight), 1000).stay()
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(0.0)
        assertThat(actual[player1]).isEqualTo(1000.0)
        assertThat(actual[player2]).isEqualTo(-1000.0)
    }

    @Test
    fun `배당금 계산 - 딜러(19), 참가자1(블랙잭), 참가자2(18)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTen, SpadeNine))
        val player1 = Player("pobi", PlayingCards(SpadeAce, SpadeQueen), 1000)
        val player2 = Player("jason", PlayingCards(SpadeKing, SpadeEight), 1000).stay()
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(-500.0)
        assertThat(actual[player1]).isEqualTo(1500.0)
        assertThat(actual[player2]).isEqualTo(-1000.0)
    }
    @Test
    fun `배당금 계산 - 딜러(19), 참가자1(블랙잭), 참가자2(버스트)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTen, SpadeNine))
        val player1 = Player("pobi", PlayingCards(SpadeAce, SpadeQueen), 1000)
        val player2 = Player("jason", PlayingCards(SpadeSeven, SpadeEight, SpadeNine), 1000)
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(-500.0)
        assertThat(actual[player1]).isEqualTo(1500.0)
        assertThat(actual[player2]).isEqualTo(-1000.0)
    }

    @Test
    fun `배당금 계산 - 딜러(18), 참가자1(18), 참가자2(18)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTwo, SpadeSeven, SpadeNine))
        val player1 = Player("pobi", PlayingCards(SpadeEight, SpadeJack), 1000).stay()
        val player2 = Player("jason", PlayingCards(SpadeThree, SpadeFive, SpadeTen), 1000).stay()
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(0.0)
        assertThat(actual[player1]).isEqualTo(0.0)
        assertThat(actual[player2]).isEqualTo(0.0)
    }

    @Test
    fun `배당금 계산 - 딜러(18), 참가자1(18), 참가자2(17)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTwo, SpadeSeven, SpadeNine))
        val player1 = Player("pobi", PlayingCards(SpadeEight, SpadeJack), 1000).stay()
        val player2 = Player("jason", PlayingCards(SpadeThree, SpadeFour, SpadeTen), 1000).stay()
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(1000.0)
        assertThat(actual[player1]).isEqualTo(0.0)
        assertThat(actual[player2]).isEqualTo(-1000.0)
    }

    @Test
    fun `배당금 계산 - 딜러(18), 참가자1(18), 참가자2(19)`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeTwo, SpadeSeven, SpadeNine))
        val player1 = Player("pobi", PlayingCards(SpadeEight, SpadeJack), 1000).stay()
        val player2 = Player("jason", PlayingCards(SpadeFive, SpadeFour, SpadeTen), 1000).stay()
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = calculateProfit(participants)

        // then
        assertThat(actual[dealer]).isEqualTo(-1000.0)
        assertThat(actual[player1]).isEqualTo(0.0)
        assertThat(actual[player2]).isEqualTo(1000.0)
    }
}
