package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.DealerResult
import blackjack.domain.PlayResultType
import blackjack.domain.Players
import blackjack.domain.SuitType
import blackjack.domain.ValueType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DealerTest {

    @DisplayName("딜러의 hit 여부")
    @Test
    fun checkHitForDealer() {
        val dealer = Dealer()
        dealer.addCard(Card(SuitType.CLUB, ValueType.J))
        dealer.addCard(Card(SuitType.CLUB, ValueType.K))

        val isHit = dealer.isHit

        assertThat(isHit).isEqualTo(false)
    }

    @DisplayName("A가 포함되어 있을 경우 딜러의 계산방식, A =11 일경우 21 미만")
    @Test
    fun checkDealerPointWithAce() {
        val dealer = Dealer()
        dealer.addCard(Card(SuitType.CLUB, ValueType.FOUR))
        dealer.addCard(Card(SuitType.CLUB, ValueType.FIVE))
        dealer.addCard(Card(SuitType.CLUB, ValueType.A))

        val point = dealer.point

        assertThat(point).isEqualTo(20)
    }

    @DisplayName("A가 포함되어 있을 경우 딜러의 계산방식, A =11 일경우 21 초과")
    @Test
    fun checkDealerPointWithAceMoreThanBlackjackPoint() {
        val dealer = Dealer()
        dealer.addCard(Card(SuitType.CLUB, ValueType.FOUR))
        dealer.addCard(Card(SuitType.CLUB, ValueType.SIX))
        dealer.addCard(Card(SuitType.CLUB, ValueType.K))
        dealer.addCard(Card(SuitType.CLUB, ValueType.A))

        val point = dealer.point

        assertThat(point).isEqualTo(21)
    }

    @DisplayName("딜러의 승무패 확인")
    @Test
    fun checkDealerResult() {
        val dealerResult = DealerResult(6)
        val players = Players.newInstance("ace,hi,con,race,toy,sky")
        val onlyPlayers = players!!.players.filterNot { it is Dealer }.asSequence()

        dealerResult.setStatic(3, 2, onlyPlayers)

        assertAll(
            { assertThat(dealerResult.getStatic()[PlayResultType.WIN]).isEqualTo(3) },
            { assertThat(dealerResult.getStatic()[PlayResultType.LOSE]).isEqualTo(2) },
            { assertThat(dealerResult.getStatic()[PlayResultType.DRAW]).isEqualTo(1) }
        )
    }
}
