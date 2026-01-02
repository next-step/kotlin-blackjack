package service

import domain.bet.BetMoney
import domain.card.CardDeck
import domain.participant.Dealer
import domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardDistributorServiceTest {
    private val cardDistributorService = CardDistributorService(CardDeck())

    @Test
    @DisplayName("플레이어와 딜러에게 각각 2장의 카드를 분배한다.")
    fun distributeTest() {
        // given
        val player = Player("A", BetMoney(10000))
        val dealer = Dealer()

        // when
        cardDistributorService.distributeInitialCards(listOf(player, dealer))

        // then
        assertThat(player.hand.handSize()).isEqualTo(CardDistributorService.INITIAL_CARD_COUNT)
        assertThat(dealer.hand.handSize()).isEqualTo(CardDistributorService.INITIAL_CARD_COUNT)
    }

    @Test
    @DisplayName("플레이어와 딜러에게 각각 2장의 카드를 분배한다.")
    fun additionalDistributeTest() {
        // given
        val player = Player("A", BetMoney(10000))
        val dealer = Dealer()
        cardDistributorService.distributeInitialCards(listOf(player, dealer))
        val expectedDealerCardSize =
            if (dealer.score() > CardDistributorService.DEALER_ADDITIONAL_CARD_THRESHOLD) {
                CardDistributorService.INITIAL_CARD_COUNT
            } else {
                CardDistributorService.INITIAL_CARD_COUNT + 1
            }

        // when
        cardDistributorService.distributeAdditionalCardsForDealer(dealer)

        // then
        assertThat(dealer.hand.handSize()).isEqualTo(expectedDealerCardSize)
    }
}
